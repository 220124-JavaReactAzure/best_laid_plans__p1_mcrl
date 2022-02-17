package com.revature.wedding_planner.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User implements Serializable{
	@Id
	@Column(name="user_id")
	private String id;
	@Column(name="user_name")
	private String name;
	@Column(name="user_email", unique = true, nullable = false)
	private String email;
	@Column(name="user_username", unique = true, nullable = false)
	private String username;
	@Column(name="user_password", nullable = false)
	private String password;
	@Column(name="user_meal_choice")
	private int mealChoice;
	@Column(name="user_plus_one")
	private boolean plusOne;
	@Column(name="user_is_attending")
	private boolean attending;
	@Column(name="user_type")
	private int userType;
	
	//TODO figure out relational mapping between tables.
	@Column(name="wedding_id")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "wedding_id")
	private String weddingId;

	public User() {
		super();
	}

	public User(String id, String name, String email, String username, String password, int userType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMealChoice() {
		return mealChoice;
	}

	public void setMealChoice(int mealChoice) {
		this.mealChoice = mealChoice;
	}

	public boolean isPlusOne() {
		return plusOne;
	}

	public void setPlusOne(boolean plusOne) {
		this.plusOne = plusOne;
	}

	public boolean isAttending() {
		return attending;
	}

	public void setAttending(boolean attending) {
		this.attending = attending;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getWeddingId() {
		return weddingId;
	}

	public void setWeddingId(String weddingId) {
		this.weddingId = weddingId;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", username=" + username + ", mealChoice=" + mealChoice + ", plusOne=" + plusOne + ", attending=" + attending
				+ ", userType=" + userType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attending, email, id, mealChoice, name, password, plusOne, userType, username, weddingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return attending == other.attending && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(mealChoice, other.mealChoice) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && plusOne == other.plusOne && userType == other.userType
				&& Objects.equals(username, other.username) && Objects.equals(weddingId, other.weddingId);
	}


	
}
