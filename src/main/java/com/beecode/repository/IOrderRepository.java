package com.beecode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beecode.entity.Order;
import com.beecode.projection.OrderProjection;
import com.beecode.projection.OrderTotalProjection;

public interface IOrderRepository extends JpaRepository<Order, Long>{
	
	public List<Optional<OrderProjection>> findAllByPersonId(Long id);
	
	public List<OrderProjection> findAllProjetedBy();
	
	public List<OrderTotalProjection> findTotalProjectedBy();

}
