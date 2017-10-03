package io.pivotal.om.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.pivotal.om.domain.Order;
import io.pivotal.om.domain.OrderReply;
import io.pivotal.om.domain.OrderRequest;

@RestController
public class UIServices {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value="api/order/{id}", method=RequestMethod.GET)
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
	
	@RequestMapping(value="api/order", method=RequestMethod.POST)
	public void placeOrder(OrderRequest orderRequest) {
		
		String url = "http://exchange.apps.pcf.guru/api/order/{id}";
		restTemplate.put("http://exchange.apps.pcf.guru/api/order", orderRequest);
//		restTemplate.exchange(url, RequestMethod.PUT, orderRequest, OrderRequest.class, uriVariables)

		
		Order order = new Order();
		order.setClientId(orderRequest.getClientId());
		order.setClOrdId(orderRequest.getClOrdId());
		order.setPrice(orderRequest.getPrice());
		order.setSide(orderRequest.getSide());
		order.setOrderQty(orderRequest.getOrderQty());
		order.setOrdType(orderRequest.getOrdType());
		
		return;
	}
	
}
