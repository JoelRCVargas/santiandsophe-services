package com.beecode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.beecode.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "tb_product_order")
public class ProductOrder extends AbstractEntity {
	
	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
	@JsonBackReference(value = "product-order")
	private Order order;

	@Column(name= "product_name", nullable = false)
	private String productName;
	
	@Column(name = "product_sku", nullable = false) 
	private String productSku;
	
	@Column(name = "unit_price", nullable = false)
	private double unitPrice;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;

	public ProductOrder() {
	
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
