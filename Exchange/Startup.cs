using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using Almirex.Contracts.Fields;
using Almirex.OrderMatchingEngine;
using Almirex.OrderMatchingEngine.Utils;
using Exchange.Models;
using Exchange.Repository;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Formatters.Json;
using Microsoft.AspNetCore.Mvc.Cors.Internal;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using MoreLinq;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using Pivotal.Discovery.Client;
using Steeltoe.CloudFoundry.Connector.MySql.EFCore;
using Steeltoe.CloudFoundry.Connector.Rabbit;
using Steeltoe.Extensions.Configuration;
using Steeltoe.Extensions.Configuration.CloudFoundry;

namespace Exchange
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {

            services.AddOptions();
            
            services.Configure<Steeltoe.Discovery.Client.SpringConfig>(Configuration.GetSection("spring"));
            services.AddMvc().AddJsonOptions(options => ConfigureSerializer(options.SerializerSettings));
            services.AddCors();
            services.AddDiscoveryClient(Configuration);
            
            services.AddDbContext<ExchangeContext>(opt => opt.UseMySql(Configuration));
            services.AddSingleton<OrderbookService>();
            services.AddRabbitConnection(Configuration);
            JsonConvert.DefaultSettings = () => ConfigureSerializer(new JsonSerializerSettings());
        }



        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            
            app.UseCors(builder => {
                builder.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader();
            });
            app.UseMvc();
            app.UseDiscoveryClient();
        }



        private JsonSerializerSettings ConfigureSerializer(JsonSerializerSettings serializer)
        {
            serializer.Formatting = Formatting.Indented;
//            serializer.NullValueHandling = NullValueHandling.Ignore;
//            serializer.DefaultValueHandling = DefaultValueHandling.Ignore;
            serializer.MissingMemberHandling = MissingMemberHandling.Ignore;
            serializer.Converters = new List<JsonConverter> {new StringEnumConverter()};
            return serializer;

        }
    }
}
