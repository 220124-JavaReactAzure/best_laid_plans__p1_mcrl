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
public class UserTypes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_type_id")
	private int id;
	

	@Column(name = "user_type")
	private String userType;
	
	@OneToMany(mappedBy="userType", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="userType")
	private List<User> users;
	
	public UserTypes() {
		super();
	}

	public UserTypes(String userType) {
		super();
		this.userType = userType;
	}

	public UserTypes(String userType, List<User> users) {
		super();
		this.userType = userType;
		this.users = users;
	}

	public UserTypes(int id, String userType, List<User> users) {
		super();
		this.id = id;
		this.userType = userType;
		this.users = users;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserTypes [userType=" + userType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userType, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTypes other = (UserTypes) obj;
		return Objects.equals(id, other.id) && Objects.equals(userType, other.userType)
				&& Objects.equals(users, other.users);
	}

	
	
	
}
