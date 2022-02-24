package com.revature.wedding_planner.models;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonManagedReference; 

@Entity
@Table(name="vendor_types")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VendorType {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vendor_type_id")
	private int vendTypeId;
	
	@Column(name="vendor_type")
	private String vendorType;
	
	@OneToMany(mappedBy="users", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="user")
	private List<Vendor> vendors;
	
	public VendorType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorType(int vendTypeId, String vendorType) {
		super();
		this.vendTypeId = vendTypeId;
		this.vendorType = vendorType;
	}

	public VendorType(int vendTypeId, String vendorType, List<Vendor> vendors) {
		super();
		this.vendTypeId = vendTypeId;
		this.vendorType = vendorType;
		this.vendors = vendors;
	}

	public int getVendTypeId() {
		return vendTypeId;
	}

	public void setVendTypeId(int vendTypeId) {
		this.vendTypeId = vendTypeId;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public VendorType(List<Vendor> vendors) {
		super();
		this.vendors = vendors;
	}
	
	
	
	
}
