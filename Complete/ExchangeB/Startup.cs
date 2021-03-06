﻿using System.Collections.Generic;
using Exchange.Models;
using Exchange.Repository;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using Pivotal.Discovery.Client;
using Steeltoe.CloudFoundry.Connector.MySql.EFCore;
using Steeltoe.CloudFoundry.Connector.Rabbit;

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
            services.AddDbContext<ExchangeContext>(opt => opt.UseMySql(Configuration));
            services.AddMvc().AddJsonOptions(options => ConfigureSerializer(options.SerializerSettings));
            services.AddCors();
            services.AddSingleton<OrderbookService>();
            services.Configure<Steeltoe.Discovery.Client.SpringConfig>(Configuration.GetSection("spring"));
            services.AddRabbitConnection(Configuration);
            services.AddDiscoveryClient(Configuration);
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
