package io.pivotal.om.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.om.model.OrderReply;
import io.pivotal.om.model.OrderRequest;

@RestController
public class UIServices {
	
	@RequestMapping(value="/getOrder")
	public OrderReply getOrder(OrderRequest orderRequest) {
		
		OrderReply reply = new OrderReply();
		reply.setOrdStatus("Got it!");
		return reply;
		
	}
}
