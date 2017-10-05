package io.pivotal.om.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.pivotal.om.domain.ClientOrder;
import io.pivotal.om.domain.ExchangeOrderResponse;
import io.pivotal.om.domain.ClientOrderResponse;
import io.pivotal.om.domain.ClientOrderRequest;
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
	public ClientOrderResponse getOrder(ClientOrderRequest clientOrderRequest) {
		
		// Reply fields
		String execID = "";
		long orderId = 0L;
		int cumQty = 0;
		String ordStatus = "";
		double lastPx = 0.0;
		int lastQty = 0;
		ClientOrderResponse reply = new ClientOrderResponse();
		
		// Copied from request
		reply.setClientId(clientOrderRequest.getClientId());
		reply.setClOrdId(clientOrderRequest.getClOrdId());
		reply.setPrice(clientOrderRequest.getPrice());
		reply.setSide(clientOrderRequest.getSide());
		reply.setOrderQty(clientOrderRequest.getOrderQty());
		reply.setOrdType(clientOrderRequest.getOrdType());
		
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
	public void placeOrder(@RequestBody ClientOrderRequest clientOrderRequest) {
		
		ClientOrder clientOrder = new ClientOrder();
		ClientOrder newOrder = or.save(clientOrder);
		or.flush();
		long orderId = newOrder.getOrderId();
		
		logger.debug("Created new order with ID=" + orderId);
		String url = "http://exchange.apps.pcf.guru/api/order/" + orderId;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ClientOrderRequest> httpOrderRequest = new HttpEntity<>(clientOrderRequest, headers);
		ResponseEntity<ExchangeOrderResponse[]> re = restTemplate.exchange(url, HttpMethod.PUT, httpOrderRequest, ExchangeOrderResponse[].class);
		
		ExchangeOrderResponse[] eor = re.getBody();
		
		newOrder.setClientId(eor[0].getClientID());
		newOrder.setClOrdId(eor[0].getClOrdID());
		newOrder.setPrice(eor[0].getPrice());
		newOrder.setSide(eor[0].getSide());
		newOrder.setOrderQty(eor[0].getOrderQty());
		newOrder.setOrdType(eor[0].getOrdType());
		or.save(newOrder);
		or.flush();
		
		return;
	}
	
}
