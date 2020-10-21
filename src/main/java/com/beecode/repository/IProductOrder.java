package com.beecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beecode.entity.ProductOrder;

public interface IProductOrder extends JpaRepository<ProductOrder, Long> {

}
