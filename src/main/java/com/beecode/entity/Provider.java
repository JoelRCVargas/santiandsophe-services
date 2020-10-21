package com.beecode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.beecode.util.AbstractEntity;

@Entity(name = "tb_provider")
public class Provider extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "contact_first_name", nullable = true, length = 100)
	private String contactFirstName;

	@Column(name = "contact_last_name", nullable = true, length = 100)
	private String contactLastName;

	@Column(name = "address1", nullable = false, length = 150)
	private String address1;

	@Column(name = "address2", nullable = true, length = 150)
	private String address2;

	@Column(name = "city", nullable = true, length = 120)
	private String city;

	@Column(name = "country", nullable = true, length = 150)
	private String country;

	@Column(name = "phone", nullable = true, length = 20)
	private String phone;

	@Column(name = "email", nullable = true, length = 120)
	private String email;

	@Column(name = "paymentMethod", nullable = true, length = 150)
	private String paymentMethod;


	@Column(name = "domain", nullable = true)
	private String domain;

	public Provider() {

	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

}
