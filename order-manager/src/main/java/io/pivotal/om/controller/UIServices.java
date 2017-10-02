package io.pivotal.om.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.om.model.OrderReply;
import io.pivotal.om.model.OrderRequest;

@RestController
public class UIServices {
	
	@RequestMapping(value="/getOrder")
	public OrderReply getOrder(OrderRequest orderRequest) {
		
		// Reply fields
		String execID = "";
		String orderId = "";
		int cumQty = 0;
		String ordStatus = "";
		double lastPx = 0.0;
		int lastQty = 0;
		OrderReply reply = new OrderReply();
		
		// Copied from request
		reply.setClientId(orderRequest.getClientId());
		reply.setClOrdId(orderRequest.getClOrdId());
		reply.setPrice(orderRequest.getPrice());
		reply.setSide(orderRequest.getSide());
		reply.setOrderQty(orderRequest.getOrderQty());
		reply.setOrdType(orderRequest.getOrdType());
		
		reply.setExecID(execID);
		reply.setOrderId(orderId);
		reply.setCumQty(cumQty);
		reply.setOrdStatus(ordStatus);
		reply.setLastPx(lastPx);
		reply.setLastQty(lastQty);
		return reply;
		
	}
}
