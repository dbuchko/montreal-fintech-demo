package io.pivotal.om.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import io.pivotal.om.domain.ClientOrder;
import io.pivotal.om.domain.ClientOrderRequest;
import io.pivotal.om.domain.ClientOrderResponse;
import io.pivotal.om.domain.ExchangeOrderResponse;
import io.pivotal.om.repository.OrderRepository;

@RestController
public class UIServices {
	
	Logger logger = LoggerFactory.getLogger(UIServices.class);
	
	private OrderRepository or;
	private RestTemplate restTemplate;
	private DiscoveryClient discoveryClient;
	
	@Autowired
	public UIServices(OrderRepository or, RestTemplate restTemplate, DiscoveryClient discoveryClient) {
		this.or = or;
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
	}
	
	@RequestMapping(value="api/order/{id}", method=RequestMethod.GET)
	public ClientOrderResponse getOrder(@PathVariable Long id, ClientOrderRequest clientOrderRequest) {

		ClientOrder clientOrder = or.findOne(id);
		
		ClientOrderResponse reply = new ClientOrderResponse();
		
		// Copied from request
		reply.setClientId(String.valueOf(clientOrder.getClientId()));
		reply.setClOrdId(clientOrder.getClOrdId());
		reply.setPrice(clientOrder.getPrice());
		reply.setSide(clientOrder.getSide());
		reply.setOrderQty(clientOrder.getOrderQty());
		reply.setOrdType(clientOrder.getOrdType());
		
		reply.setExecId(clientOrder.getExecId());
		reply.setOrderId(clientOrder.getOrderId());
		reply.setCumQty(clientOrder.getCumQty());
		reply.setOrdStatus(clientOrder.getOrdStatus());
		reply.setLastPx(clientOrder.getLastPx());
		reply.setLastQty(clientOrder.getLastQty());
		return reply;
		
	}
	
	@RequestMapping(value="api/orders", method=RequestMethod.GET)
	public List<ClientOrderResponse> getOrders(ClientOrderRequest clientOrderRequest) {

		List<ClientOrder> clientOrders = or.findAll();
		List<ClientOrderResponse> clientOrderResponse = new ArrayList<ClientOrderResponse>();
		
		for (ClientOrder clientOrder : clientOrders) {

			ClientOrderResponse reply = new ClientOrderResponse();

			// Copied from request
			reply.setClientId(String.valueOf(clientOrder.getClientId()));
			reply.setClOrdId(clientOrder.getClOrdId());
			reply.setPrice(clientOrder.getPrice());
			reply.setSide(clientOrder.getSide());
			reply.setOrderQty(clientOrder.getOrderQty());
			reply.setOrdType(clientOrder.getOrdType());
			reply.setExecId(clientOrder.getExecId());
			reply.setOrderId(clientOrder.getOrderId());
			reply.setCumQty(clientOrder.getCumQty());
			reply.setOrdStatus(clientOrder.getOrdStatus());
			reply.setLastPx(clientOrder.getLastPx());
			reply.setLastQty(clientOrder.getLastQty());
			
			clientOrderResponse.add(reply);
		}
		
		return clientOrderResponse;
		
	}
	
	
	@RequestMapping(value="/api/exchanges", method=RequestMethod.GET)
	public List<String> getExchanges() {
		
		List<String> services = discoveryClient.getServices();
		List<String> exchanges = new ArrayList<String>();
		for (String service : services) {
			if(service.toUpperCase().startsWith("EXCHANGE_"))
				exchanges.add(service.substring("EXCHANGE_".length()).trim().toUpperCase());
		}
		return exchanges;
	}	

	
	@PostMapping(value="api/order")
	@Transactional
	@ResponseBody
	public ClientOrder placeOrder(@RequestBody ClientOrderRequest clientOrderRequest) {
		
		ClientOrder clientOrder = new ClientOrder();
		ClientOrder newOrder = or.save(clientOrder);
		or.flush();
		long orderId = newOrder.getOrderId();
		logger.debug("Created new order with ID=" + orderId);
		
		String url = lookupUrlForExchange(clientOrderRequest.getSymbol()) + "/api/order/" + String.valueOf(orderId);
		logger.debug("Exchange service URL=" + url);
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ClientOrderRequest> httpOrderRequest = new HttpEntity<>(clientOrderRequest, headers);
		ResponseEntity<ExchangeOrderResponse[]> re = restTemplate.exchange(url, HttpMethod.PUT, httpOrderRequest, ExchangeOrderResponse[].class);
		
		ExchangeOrderResponse[] eor = re.getBody();
		
		newOrder.setClientId(eor[0].getClientID());
		newOrder.setClOrdId(eor[0].getClOrdID());
		newOrder.setSymbol(eor[0].getSymbol());
		newOrder.setPrice(eor[0].getPrice());
		newOrder.setSide(eor[0].getSide());
		newOrder.setOrderQty(eor[0].getOrderQty());
		newOrder.setOrdType(eor[0].getOrdType());
		newOrder.setOrdStatus(eor[0].getOrdStatus());
		newOrder.setOrdRejReason(eor[0].getOrdRejReason());
		or.save(newOrder);
		or.flush();

		return newOrder;
	}

	  private String lookupUrlForExchange(String symbol) {
		  List<ServiceInstance> serviceInstances = discoveryClient.getInstances("Exchange_" + symbol);
		  String url = serviceInstances.get(0).getUri().toString();
		  return url;
	  }
		
}
