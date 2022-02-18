package com.revature.wedding_planner.models;

public class VendorTypes {
	private int id;
	private String vendorType;
	
	public VendorTypes() {
		super();
	}
	public VendorTypes(int id, String vendorType) {
		super();
		this.id = id;
		this.vendorType = vendorType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	
	
}
