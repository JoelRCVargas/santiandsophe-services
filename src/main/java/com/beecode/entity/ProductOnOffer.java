package com.beecode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.beecode.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "tb_product_on_offer")
public class ProductOnOffer extends AbstractEntity {

	@Lob
	@Column(name = "image", nullable = false)
	private String image;
	
	@Column(name = "description", nullable = false, length = 300)
	private String description;

	@Column(name = "status", nullable = true)
	private boolean status;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Product product;

	public ProductOnOffer() {
	}

	public ProductOnOffer(String domain, String image,String description, boolean status, Product product) {
		this.image = image;
		this.status = status;
		this.product = product;
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
