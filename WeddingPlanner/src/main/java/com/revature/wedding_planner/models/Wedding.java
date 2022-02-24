package com.revature.wedding_planner.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "wedding")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Wedding {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wedding_id")
	private int id;
	@Column(name = "wedding_date", unique = true, nullable = false)
	private String date;
	@Column(name = "wedding_name", unique = true, nullable = false)
	private String name;
	@Column(name = "head_count")
	private int headCount;
	@Column(name = "cost")
	private double cost;

//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_venue")
//	@JsonIgnoreProperties(value= {"weddings", "id"})
//	public Vendor venue;
//	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_florist")
//	@JsonIgnoreProperties(value= {"weddings", "id"})
//	public Vendor florist;
//	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_caterer")
//	@JsonIgnoreProperties(value= {"weddings", "id"})
//	public Vendor caterer;
//	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_musician")
//	@JsonIgnoreProperties(value= {"weddings", "id"})
//	public Vendor musician;
//	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_photographer")
//	@JsonIgnoreProperties(value= {"weddings", "id"})
//	public Vendor photographer;

	@OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = "wedding")
	private List<User> users;

	public Wedding() {
		super();
	}

	public Wedding(String date, String name) {
		super();
		this.date = date;
		this.name = name;
	}

	public Wedding(int id, String date, String name) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
	}

	public Wedding(int id, String date, String name, int headCount, double cost) {
		super();
		this.id = id;
		this.date = date;
		this.name = name;
		this.headCount = headCount;
		this.cost = cost;
	}

//	public Wedding(int id, String date, String name, int headCount, double cost, Vendor venue, Vendor florist, Vendor caterer,
//			Vendor musician, Vendor photographer) {
//		super();
//		this.id = id;
//		this.date = date;
//		this.name = name;
//		this.headCount = headCount;
//		this.cost = cost;
//		this.venue = venue;
//		this.florist = florist;
//		this.caterer = caterer;
//		this.musician = musician;
//		this.photographer = photographer;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeadCount() {
		return headCount;
	}

	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

//	public Vendor getVenue() {
//		return venue;
//	}
//
//	public void setVenue(Vendor venue) {
//		this.venue = venue;
//	}
//
//	public Vendor getFlorist() {
//		return florist;
//	}
//
//	public void setFlorist(Vendor florist) {
//		this.florist = florist;
//	}
//
//	public Vendor getCaterer() {
//		return caterer;
//	}
//
//	public void setCaterer(Vendor caterer) {
//		this.caterer = caterer;
//	}
//
//	public Vendor getMusician() {
//		return musician;
//	}
//
//	public void setMusician(Vendor musician) {
//		this.musician = musician;
//	}
//
//	public Vendor getPhotographer() {
//		return photographer;
//	}
//
//	public void setPhotographer(Vendor photographer) {
//		this.photographer = photographer;
//	}
//
//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//
//	@Override
//	public String toString() {
//		return "Wedding [date=" + date + ", name=" + name + ", headCount=" + headCount + ", cost=" + cost + ", venue="
//				+ venue + ", florist=" + florist + ", caterer=" + caterer + ", musician=" + musician + ", photographer="
//				+ photographer + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(caterer, cost, date, florist, headCount, id, musician, name, photographer, users, venue);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Wedding other = (Wedding) obj;
//		return caterer == other.caterer && Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost)
//				&& Objects.equals(date, other.date) && florist == other.florist && headCount == other.headCount
//				&& id == other.id && musician == other.musician && Objects.equals(name, other.name)
//				&& photographer == other.photographer && Objects.equals(users, other.users) && venue == other.venue;
//	}

}
