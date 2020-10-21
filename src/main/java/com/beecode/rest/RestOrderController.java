package com.beecode.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beecode.entity.Order;
import com.beecode.entity.Person;
import com.beecode.interfaces.OrderService;
import com.beecode.interfaces.PersonService;
import com.beecode.projection.OrderProjection;
import com.beecode.projection.OrderTotalProjection;

@RestController
public class RestOrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private PersonService personService;

	@GetMapping(value = "/public/orders/all", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Order>> getAllOrder() {
		return ResponseEntity.ok().body(orderService.getAllOrder());
	}
	
	@GetMapping(value = "/admin/orders/custom/all", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<OrderProjection>> getAllOrderProjection() {
		return ResponseEntity.ok().body(orderService.getAllOrderProjection());
	}
	
	@GetMapping(value = "/admin/total/orders", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<OrderTotalProjection>> getTotalOrder() {
		return ResponseEntity.ok().body(orderService.getOrderTotalProjection());
	}
	
	@PostMapping(value = "/admin/orders/change/delivery/status", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Order> changeDeliveryStatusOrder(@RequestBody Order order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.changeDeliveryStatusOrder(order));
	}
	
	@GetMapping(value = "/private/order/by/person", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Optional<OrderProjection>>> getProjectionOrderByPersonId(@RequestParam("id_person") Long id) {
		return ResponseEntity.ok().body(orderService.getOrderProjectionByPersonId(id));
	}
	
	@GetMapping(value = "/admin/count/orders", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Long> getCountOrders() {
		return ResponseEntity.ok().body(orderService.getCountOrder());
	}

	@PostMapping(value = "/public/order/create", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		System.out.println(order.getCurrency());
		Person personCreate = personService.createPerson(order.getPerson());
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order, personCreate));
	}
	
	@PostMapping(value = "/public/order/update", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @RequestParam("dues") int dues, 
			@RequestParam("token") String token, @RequestParam("type_pay") String typePay) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrder(order,dues, token,typePay));
	}

}
