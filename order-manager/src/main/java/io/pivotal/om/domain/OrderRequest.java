package io.pivotal.om.domain;

public class OrderRequest {

	private String clientId;
	private String clOrdId;
	private double price;
	private String side;
	private int orderQty;
	private String ordType;
	
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "clientId=" + clientId +
				", clOrdId=" + clOrdId +
				", price=" + price +
				", side=" + side +
				", ordType=" + ordType;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
