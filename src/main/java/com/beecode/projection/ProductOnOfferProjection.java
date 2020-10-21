package com.beecode.projection;

public interface ProductOnOfferProjection {
	
	Long getId();
	String getImage();
	String getDescription();
	ProductProjection getProduct();
	
	public interface ProductProjection {
		boolean getStatusDiscount();
		double getDiscount();
		String getName();
		String getPicturePrincipal();
		String getSku();
	}
}
