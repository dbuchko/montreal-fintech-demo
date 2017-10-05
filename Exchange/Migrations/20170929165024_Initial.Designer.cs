﻿// <auto-generated />
using Almirex.Contracts.Fields;
using Exchange.Repository;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage;
using Microsoft.EntityFrameworkCore.Storage.Internal;
using System;

namespace Exchange.Migrations
{
    [DbContext(typeof(ExchangeContext))]
    [Migration("20170929165024_Initial")]
    partial class Initial
    {
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("MySql:ValueGenerationStrategy", MySqlValueGenerationStrategy.IdentityColumn)
                .HasAnnotation("ProductVersion", "2.0.0-rtm-26452");

            modelBuilder.Entity("Almirex.Contracts.Messages.ExecutionReport", b =>
                {
                    b.Property<string>("ExecId")
                        .ValueGeneratedOnAdd();

                    b.Property<long>("AvgPx");

                    b.Property<string>("ClOrdID");

                    b.Property<string>("ClientID");

                    b.Property<long>("CumQty");

                    b.Property<long>("CummCommission");

                    b.Property<int?>("CxlRejReason");

                    b.Property<bool>("EscrowRestricted");

                    b.Property<string>("ExecRefID");

                    b.Property<int>("ExecType");

                    b.Property<long>("Fee");

                    b.Property<long>("LastCommission");

                    b.Property<int>("LastLiquidityInd");

                    b.Property<long>("LastPx");

                    b.Property<long>("LastQty");

                    b.Property<long>("LeavesEscrow");

                    b.Property<string>("MassStatusReqID");

                    b.Property<int?>("OrdRejReason");

                    b.Property<int>("OrdStatus");

                    b.Property<string>("OrdStatusReqID");

                    b.Property<int>("OrdType");

                    b.Property<string>("OrderId");

                    b.Property<long>("OrderQty");

                    b.Property<string>("OrigClOrdID");

                    b.Property<long>("OrigEscrow");

                    b.Property<long?>("PegOffset");

                    b.Property<int?>("PegPriceType");

                    b.Property<int?>("PegScope");

                    b.Property<long>("PeggedPrice");

                    b.Property<long>("Price");

                    b.Property<string>("SecondaryOrderId");

                    b.Property<long>("SeqNum");

                    b.Property<int>("Side");

                    b.Property<long>("StopPx");

                    b.Property<DateTime?>("SubmitTime");

                    b.Property<string>("Symbol");

                    b.Property<int>("TimeInForce");

                    b.Property<int?>("TotalNumReports");

                    b.Property<string>("TradeId");

                    b.Property<DateTime>("TransactTime");

                    b.Property<string>("TrdMatchID");

                    b.Property<int?>("TriggerPriceType");

                    b.HasKey("ExecId");

                    b.HasIndex("OrdStatus");

                    b.ToTable("ExecutionReport");
                });
#pragma warning restore 612, 618
        }
    }
}
