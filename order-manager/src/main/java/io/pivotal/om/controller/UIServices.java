package io.pivotal.om.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.pivotal.om.domain.ClientOrder;
import io.pivotal.om.domain.OrderReply;
import io.pivotal.om.domain.OrderRequest;
import io.pivotal.om.repository.OrderRepository;

@RestController
public class UIServices {
	
	Logger logger = LoggerFactory.getLogger(UIServices.class);
	
	private OrderRepository or;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public UIServices(OrderRepository or, RestTemplate restTemplate) {
		this.or = or;
		this.restTemplate = restTemplate;
	}
	
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
	
	@PostMapping(value="api/order")
	@Transactional
	public void placeOrder(@RequestBody OrderRequest orderRequest) {
		
		ClientOrder clientOrder = new ClientOrder();
		ClientOrder newOrder = or.save(clientOrder);
		or.flush();
		long orderId = newOrder.getOrderId();
		
		logger.debug("Created new order with ID=" + orderId);
		String url = "http://exchange.apps.pcf.guru/api/order/" + orderId;
//		restTemplate.put("http://exchange.apps.pcf.guru/api/order", orderRequest);
//		restTemplate.exchange(url, RequestMethod.PUT, orderRequest, OrderRequest.class, uriVariables)
		
		logger.debug("orderRequest: " + orderRequest.toString());
		newOrder.setClientId(orderRequest.getClientId());
		newOrder.setClOrdId(orderRequest.getClOrdId());
		newOrder.setPrice(orderRequest.getPrice());
		newOrder.setSide(orderRequest.getSide());
		newOrder.setOrderQty(orderRequest.getOrderQty());
		newOrder.setOrdType(orderRequest.getOrdType());
		or.save(newOrder);
		or.flush();
		
		return;
	}
	
}
