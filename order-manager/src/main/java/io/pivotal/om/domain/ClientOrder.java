package io.pivotal.om.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	
	private String clientId;
	private String clOrdId;
	private String symbol;
	private int price;
	private String side;
	private int orderQty;
	private String ordType;
	private String execId;
	private int cumQty;
	private String ordStatus;
	private String ordRejReason;
	private int lastPx;
	private int lastQty;

	public ClientOrder() {
		super();
	}
	
	public ClientOrder(long orderId, String clientId, String clOrdId, String symbol, int price, String side, int orderQty, String ordType,
			String execId, int cumQty, String ordStatus, String ordRejReason, int lastPx, int lastQty) {
		super();
		this.orderId = orderId;
		this.clientId = clientId;
		this.clOrdId = clOrdId;
		this.symbol = symbol;
		this.price = price;
		this.side = side;
		this.orderQty = orderQty;
		this.ordType = ordType;
		this.execId = execId;
		this.cumQty = cumQty;
		this.ordStatus = ordStatus;
		this.ordRejReason = ordRejReason;
		this.lastPx = lastPx;
		this.lastQty = lastQty;
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
	
	public String getExecId() {
		return execId;
	}

	public void setExecId(String execId) {
		this.execId = execId;
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

	public String getOrdRejReason() {
		return ordRejReason;
	}

	public void setOrdRejReason(String ordRejReason) {
		this.ordRejReason = ordRejReason;
	}

	public int getLastPx() {
		return lastPx;
	}

	public void setLastPx(int lastPx) {
		this.lastPx = lastPx;
	}

	public int getLastQty() {
		return lastQty;
	}

	public void setLastQty(int lastQty) {
		this.lastQty = lastQty;
	}

}
