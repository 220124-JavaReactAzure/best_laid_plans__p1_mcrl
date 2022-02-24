package com.revature.wedding_planner.models;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int id;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_email", unique = true, nullable = false)
	private String email;
	@Column(name = "user_username", unique = true, nullable = false)
	private String username;
	@Column(name = "user_password", nullable = false)
	private String password;
	@Column(name = "user_plus_one")
	private boolean plusOne;
	@Column(name = "user_is_attending")
	private boolean attending;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "meal_choice")
	@JsonIgnoreProperties(value= {"users", "id"})
	public MealType mealChoice;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "wedding_id")
	@JsonIgnoreProperties(value= {"users", "name", "date", "headCount", 
			"cost", /*"venue", "florist", "caterer","musician", "photographer"*/})
	public Wedding wedding;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_type")
	@JsonIgnoreProperties(value= {"users", "id"})
	public UserType userType;

	public User() {
		super();
	}

	public User(String name, String email, String username, String password) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User(String name, String email, String username, String password, UserType userType) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	public User(String name, String email, String username, String password, MealType mealChoice) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.mealChoice = mealChoice;
	}

	public User(int id, String name, String email, String username, String password, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public User(int id, String name, String email, String username, String password, MealType mealChoice,
			boolean plusOne, boolean attending, UserType userType, Wedding wedding) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.mealChoice = mealChoice;
		this.plusOne = plusOne;
		this.attending = attending;
		this.userType = userType;
		this.wedding = wedding;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public MealType getMealChoice() {
		return mealChoice;
	}

	public void setMealChoice(MealType mealChoice) {
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Wedding getWeddingId() {
		return wedding;
	}

	public void setWedding(Wedding wedding) {
		this.wedding = wedding;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", username=" + username + ", mealChoice=" + mealChoice + ", plusOne=" + plusOne + ", attending=" + attending
				+ ", userType=" + userType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attending, email, id, mealChoice, name, password, plusOne, userType, username, wedding);
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
				&& Objects.equals(username, other.username) && Objects.equals(wedding, other.wedding);
	}

}
