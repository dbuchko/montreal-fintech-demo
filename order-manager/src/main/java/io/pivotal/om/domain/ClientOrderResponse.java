package io.pivotal.om.domain;

public class ClientOrderResponse extends ClientOrderRequest {

	private String execId;
	private int cumQty;
	private String ordStatus;
	private double lastPx;
	private int lastQty;
	
	public ClientOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
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
