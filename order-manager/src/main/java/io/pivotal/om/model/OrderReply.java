package io.pivotal.om.model;

public class OrderReply extends OrderRequest {

	private String execID;
	private Integer cumQty;
	private String ordStatus;
	private Float lastPx;
	private Integer lastQty;
	
	public OrderReply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getExecID() {
		return execID;
	}

	public void setExecID(String execID) {
		this.execID = execID;
	}

	public Integer getCumQty() {
		return cumQty;
	}

	public void setCumQty(Integer cumQty) {
		this.cumQty = cumQty;
	}

	public String getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Float getLastPx() {
		return lastPx;
	}

	public void setLastPx(Float lastPx) {
		this.lastPx = lastPx;
	}

	public Integer getLastQty() {
		return lastQty;
	}

	public void setLastQty(Integer lastQty) {
		this.lastQty = lastQty;
	}
	
	
	
}
