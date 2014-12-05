package com.brianghig.contact.model;

import java.util.ArrayList;
import java.util.Collection;

public class ContactInfo {

	private String name;
	private String email;
	private String organization;
	private String phone;
	private Collection<String> other;
	
	public String getName() {
		return name;
	}
	public ContactInfo setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public ContactInfo setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getOrganization() {
		return organization;
	}
	public ContactInfo setOrganization(String organization) {
		this.organization = organization;
		return this;
	}
	public String getPhone() {
		return phone;
	}
	public ContactInfo setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	public Collection<String> getOther() {
		initOther();
		return other;
	}
	public ContactInfo setOther(Collection<String> other) {
		this.other = other;
		return this;
	}
	public ContactInfo addOther(String other) {
		initOther();
		this.other.add(other);
		return this;
	}
	
	/**
	 * Initialize the 'other' collection
	 * as a simple array list
	 */
	private void initOther() {
		if(this.other == null) {
			this.other = new ArrayList<String>();
		}
	}
}
