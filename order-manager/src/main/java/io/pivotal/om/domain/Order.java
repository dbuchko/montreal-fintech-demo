package io.pivotal.om.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Order {
	
	@Id
	private String orderId;
	
	private String clientId;
	private String clOrdId;
	private double price;
	private String side;
	private int orderQty;
	private String ordType;
	private String execID;
	private int cumQty;
	private String ordStatus;
	private double lastPx;
	private int lastQty;

	public Order() {
		super();
		this.orderId = UUID.randomUUID().toString();
	}
	
	public Order(String orderId, String clientId, String clOrdId, double price, String side, int orderQty, String ordType,
			String execID, int cumQty, String ordStatus, double lastPx, int lastQty) {
		super();
		this.orderId = orderId;
		this.clientId = clientId;
		this.clOrdId = clOrdId;
		this.price = price;
		this.side = side;
		this.orderQty = orderQty;
		this.ordType = ordType;
		this.execID = execID;
		this.cumQty = cumQty;
		this.ordStatus = ordStatus;
		this.lastPx = lastPx;
		this.lastQty = lastQty;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
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
	
	public String getExecID() {
		return execID;
	}

	public void setExecID(String execID) {
		this.execID = execID;
	}

	public int getCumQty() {
		return cumQty;
	}

	public void setCumQty(int cumQty) {
		this.cumQty = cumQty;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public double getLastPx() {
		return lastPx;
	}

	public void setLastPx(double lastPx) {
		this.lastPx = lastPx;
	}

	public int getLastQty() {
		return lastQty;
	}

	public void setLastQty(int lastQty) {
		this.lastQty = lastQty;
	}

}
