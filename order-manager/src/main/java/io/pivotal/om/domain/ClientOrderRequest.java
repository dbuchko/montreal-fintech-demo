package io.pivotal.om.domain;

public class ClientOrderRequest {

	private long orderId;
	private String clientId;
	private String clOrdId;
	private String symbol;
	private int price;
	private String side;
	private int orderQty;
	private String ordType;
	
	public ClientOrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "clientId=" + clientId +
				", clOrdId=" + clOrdId +
				", price=" + price +
				", symbol=" + symbol +
				", side=" + side +
				", ordType=" + ordType;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClOrdId() {
		return clOrdId;
	}
	
	public void setClOrdId(String clOrdId) {
		this.clOrdId = clOrdId;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrdType() {
		return ordType;
	}
	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}
	
	
}
