using System;
using System.Collections.Generic;
using System.Linq;
using System.Reactive.Disposables;
using System.Reactive.Linq;
using System.Text;
using System.Threading.Tasks;
using Almirex.Contracts.Fields;
using MarketDataServer.Hubs;
using Microsoft.AspNetCore.SignalR;
using Newtonsoft.Json;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using Almirex.Contracts.Messages;
using Microsoft.Extensions.DependencyInjection;

namespace MarketDataServer
{
    public class Tick
    {
        public Tick()
        {
        }

        public Tick(long price, long lastPrice)
        {
            if (lastPrice == 0)
                Change = 0;
            else
                Change = (price - lastPrice) * 100 / lastPrice;
        }

        public long Price { get; set; }
        public long Change { get; set; }
        public string Symbol { get; set; }
    }
    public class DataPusher : IDisposable
    {
        private readonly ConnectionFactory _rabbitConnection;
        private readonly IHubContext<MarketDataHub> _hubContext;
        readonly CompositeDisposable _disposable = new CompositeDisposable();
        private IConnection _connection;
        private IModel _channel;
        public DataPusher(IServiceProvider serviceProvider,IHubContext<MarketDataHub> hubContext)
        {
            var scope = serviceProvider.CreateScope();
            _disposable.Add(scope);
            _rabbitConnection = scope.ServiceProvider.GetService<ConnectionFactory>(); ;
            _hubContext = hubContext;
        }

        public IDisposable StartPushing()
        {

            _connection = _rabbitConnection.CreateConnection();
            _channel = _connection.CreateModel();
            _channel.QueueDeclare("mds1", false, false, false, null);
            _channel.ExchangeDeclare(exchange: "execution-reports", type: "fanout", durable: false, autoDelete: false);
            _channel.QueueBind("mds1", "execution-reports", "#", null);
            var consumer = new EventingBasicConsumer(_channel);
            _channel.BasicConsume("mds1", true, consumer);
            
            
            var observable = Observable.FromEventPattern<EventHandler<BasicDeliverEventArgs>, BasicDeliverEventArgs>(h => consumer.Received += h, h => consumer.Received -= h);
            var handle = observable
                .Select(x => JsonConvert.DeserializeObject<List<ExecutionReport>>(Encoding.UTF8.GetString(x.EventArgs.Body)))
                .SelectMany(x => x)
                .Where(x => x.ExecType == ExecType.Trade)
                .GroupByUntil(x => x.TradeId, x => x.Buffer(2))
                .SelectMany(x => x.FirstAsync())
                .Scan(new Tick(), (tick, report) => new Tick
                {
                    Price = report.LastPx,
                    Change = tick.Price == 0 ? 0 : (report.LastPx - tick.Price) * 100 / tick.Price,
                    Symbol = report.Symbol
                })
                .Do(tick => _hubContext.Clients.All.InvokeAsync("tick", tick))
                .Subscribe();
            _disposable.Add(handle);
            return handle;
                

        }
        public void Dispose()
        {
            _disposable.Dispose();

        }
    }
}
