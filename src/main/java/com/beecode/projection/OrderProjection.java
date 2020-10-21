package com.beecode.projection;

import java.util.List;

public interface OrderProjection {
	
	Long getId();
	String getCurrency();
	String getDescription();
	String getTokenPay();
	String getTypePay();
	int getDues();
	double getDuesTotal();
	double getTotal();
	boolean getPaymentStatus();
	boolean getDeliveryStatus();
	List<ProductOrderProjection> getProductOrder();
	PersonOrderProjection getPerson();
	
	public interface ProductOrderProjection{
		Long getId();
		String getProductName();
		String getProductSku();
		double getUnitPrice();
		int getQuantity();
	}
	
	public interface PersonOrderProjection{
		Long getId();
		String getFirstName();
		String getLastName();
	}
}
