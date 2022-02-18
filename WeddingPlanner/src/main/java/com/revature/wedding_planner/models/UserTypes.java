package com.revature.wedding_planner.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_types")
public class UserTypes {
	@Id
	@OneToMany(mappedBy="users", fetch=FetchType.EAGER)
	@JoinColumn(name="user_type_id")
	private String id;
	
	//@JsonIgnoreProperties(value="users")
	//private List<User> userTypes;
	@Column(name = "user_type")
	private String userType;
	
	public UserTypes() {
		super();
	}

	public UserTypes(String id, String userType) {
		super();
		this.id = id;
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
