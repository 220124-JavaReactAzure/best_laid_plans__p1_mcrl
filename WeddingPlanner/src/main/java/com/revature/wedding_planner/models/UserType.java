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

@Entity
@Table(name="user_types")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_type_id")
	private int id;
	
	//try without columnDefinition and/or check azure column definition syntax
	@Column(name = "user_type", unique = true, nullable = false)
	private String userType;
	
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="userType")
	private List<User> users;
	
	public UserType() {
		super();
	}

	public UserType(String userType) {
		super();
		this.userType = userType;
	}

	public UserType(int id, String userType) {
		super();
		this.id = id;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserTypes [userType=" + userType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserType other = (UserType) obj;
		return Objects.equals(id, other.id) && Objects.equals(userType, other.userType);
	}

	
	
	
}
