package com.beecode.projection;

public interface BannerProjection {
	
	Long getId();
	String getImage();
	String getDomain();
	String getTitle();
	String getSubtitle();
	String getDescription();
	ProductProjection getProduct();
	
	public interface ProductProjection{

		Long getId();
		String getSku();
	}
	
}
