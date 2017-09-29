package io.pivotal.om.model;

public class OrderRequest {

	private String clientId;
	private String clOrdId;
	private String orderId;
	private Float price;
	private String side;
	private Integer orderQty;
	private String ordType;
	
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public String getOrdType() {
		return ordType;
	}
	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}
	
	
}
