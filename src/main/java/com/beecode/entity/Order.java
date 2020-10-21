package com.beecode.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.beecode.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "tb_order")
@JsonIgnoreProperties
public class Order extends AbstractEntity{
	public static final String PAYPAL = "PAYPAL";
	public static final String CREDIt_CARD = "CREDID_CARD";
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonManagedReference(value="product-order")
	@Column(updatable = false)
	private List<ProductOrder> productOrder;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person", nullable = true)
	@JsonBackReference(value="person-order")
	private Person person;
	
	@Column(name = "type_pay", nullable = true, length = 50)
	private String typePay;
	
	@Column(name = "currency", nullable = true, length = 50)
	private String currency;
	
	@Column(name = "description", nullable = true, length = 150)
	private String description;
	
	@Column(name = "token_pay", nullable = true, length = 50)
	private String tokenPay;
	
	@Column(name = "dues", nullable = false)
	private int dues;
	
	@Column(name = "dues_total", nullable = true)
	private double duesTotal;
	
	@Column(name = "total", nullable = false)
	private double total;
	
	@Column(name = "payment_status", nullable = false)
	private boolean paymentStatus;
	
	@Column(name = "delivery_status", nullable = false)
	private boolean deliveryStatus;
	
	public Order() {
		
	}

	public List<ProductOrder> getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(List<ProductOrder> productOrder) {
		this.productOrder = productOrder;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTokenPay() {
		return tokenPay;
	}

	public void setTokenPay(String tokenPay) {
		this.tokenPay = tokenPay;
	}

	public int getDues() {
		return dues;
	}

	public void setDues(int dues) {
		this.dues = dues;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTypePay() {
		return typePay;
	}

	public void setTypePay(String typePay) {
		this.typePay = typePay;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public double getDuesTotal() {
		return duesTotal;
	}

	public void setDuesTotal(double duesTotal) {
		this.duesTotal = duesTotal;
	}
	
}
