package com.beecode.interfaces;

import java.util.List;
import java.util.Optional;

import com.beecode.entity.Order;
import com.beecode.entity.Person;
import com.beecode.projection.OrderProjection;
import com.beecode.projection.OrderTotalProjection;

public interface OrderService {
	
	public Order createOrder(Order order, Person person);
	public Order updateOrder(Order order, int dues , String token, String typePay);
	public List<Order> getAllOrder();
	public void deleteOrder(Long orderId);
	public List<Optional<OrderProjection>> getOrderProjectionByPersonId(Long id);
	public List<OrderProjection> getAllOrderProjection();
	public Order changeDeliveryStatusOrder(Order order);
	public Long getCountOrder();
	public List<OrderTotalProjection> getOrderTotalProjection();
}
