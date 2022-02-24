package com.revature.wedding_planner.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "vendors")
//TODO check if generator can be omitted for non-serial id types
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Vendor {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id", unique = true, nullable = false)
	private int vendId;
	
	@Column(name = "vendor_name")
	private String vendorName;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_type_id")
	@JsonIgnoreProperties(value= {"vendors", "id"})
	public VendorType vendortype;

	
	@Column(name = "vendor_cost")
	private double cost;
	
	@Column(name = "vendor_availability_begin_date")
	private Date availBeginDate;
	
	@Column(name = "vendor_availability_end_date")
	private Date availEndDate;

	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vendor(int id, String name, VendorType vendortype, double cost, Date availBeginDate, Date availEndDate) {
		super();
		this.vendId = id;
		this.vendorName = name;
		this.vendortype = vendortype;
		this.cost = cost;
		this.availBeginDate = availBeginDate;
		this.availEndDate = availEndDate;
	}

	public int getId() {
		return vendId;
	}

	public void setId(int id) {
		this.vendId = id;
	}

	public String getName() {
		return vendorName;
	}

	public void setName(String name) {
		this.vendorName = name;
	}

	public VendorType getVendortype() {
		return vendortype;
	}

	public void setVendortype(VendorType vendortype) {
		this.vendortype = vendortype;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getAvailBeginDate() {
		return availBeginDate;
	}

	public void setAvailBeginDate(Date availBeginDate) {
		this.availBeginDate = availBeginDate;
	}

	public Date getAvailEndDate() {
		return availEndDate;
	}

	public void setAvailEndDate(Date availEndDate) {
		this.availEndDate = availEndDate;
	}

}
