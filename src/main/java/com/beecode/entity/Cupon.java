package com.beecode.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.beecode.util.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "tb_cupon")
public class Cupon extends AbstractEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "discount", nullable = false)
	private double discount;

	@Column(name = "status", nullable = false)
	private boolean status;

	@Column(name = "descrition", nullable = true)
	private String description;

	@Column(name = "expiredDateTime", nullable = false)
	private String expiredDateTime;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	@Column(nullable = true)
	private LocalDateTime localExpiredDateTime;
	
	@JsonFormat(pattern = "YYYY-MM-dd")
	private LocalDateTime issueDateTime;

	public Cupon() {
		this.issueDateTime = LocalDateTime.now();
		this.status = true;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpiredDateTime() {
		return expiredDateTime;
	}

	public void setExpiredDateTime(String expiredDateTime) {
		this.expiredDateTime = expiredDateTime;
	}

	public LocalDateTime getIssueDateTime() {
		return issueDateTime;
	}

	public void setIssueDateTime(LocalDateTime issueDateTime) {
		this.issueDateTime = issueDateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getLocalExpiredDateTime() {
		return localExpiredDateTime;
	}

	public void setLocalExpiredDateTime(LocalDateTime localExpiredDateTime) {
		this.localExpiredDateTime = localExpiredDateTime;
	}
	
	
}
