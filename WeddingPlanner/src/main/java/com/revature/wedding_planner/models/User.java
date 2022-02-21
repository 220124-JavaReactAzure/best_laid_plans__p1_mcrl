package com.revature.wedding_planner.models;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "users")
//TODO check if generator can be omitted for non-serial id types
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private int id;
	@Column(name = "user_name", columnDefinition = "VARCHAR CHECK (user_name <> '')")
	private String name;
	@Column(name = "user_email", unique = true, nullable = false, columnDefinition = "VARCHAR CHECK (user_email <> '')")
	private String email;
	@Column(name = "user_username", unique = true, nullable = false, columnDefinition = "VARCHAR CHECK (user_username <> '')")
	private String username;
	@Column(name = "user_password", nullable = false, columnDefinition = "VARCHAR CHECK (user_password <> '')")
	private String password;
	@Column(name = "user_plus_one")
	private boolean plusOne;
	@Column(name = "user_is_attending")
	private boolean attending;

	// TODO this might implement relational mapping between tables. may need to add
	// nullable = false
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "meal_choice")
	@JsonIgnoreProperties(value= {"users", "id"})
	public MealTypes mealChoice;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "wedding_id")
//	public Wedding wedding;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_type_id")
//	public UserTypes userType;

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

	public User(String name, String email, String username, String password, UserTypes userType) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
//		this.userType = userType;
	}

	public User(int id, String name, String email, String username, String password, UserTypes userType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
//		this.userType = userType;
	}

	public User(int id, String name, String email, String username, String password, MealTypes mealChoice,
			boolean plusOne, boolean attending, UserTypes userType, Wedding wedding) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.mealChoice = mealChoice;
		this.plusOne = plusOne;
		this.attending = attending;
//		this.userType = userType;
//		this.wedding = wedding;
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

	public MealTypes getMealChoice() {
		return mealChoice;
	}

	public void setMealChoice(MealTypes mealChoice) {
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

//	public UserTypes getUserType() {
//		return userType;
//	}
//
//	public void setUserType(UserTypes userType) {
//		this.userType = userType;
//	}
//
//	public Wedding getWeddingId() {
//		return wedding;
//	}
//
//	public void setWedding(Wedding wedding) {
//		this.wedding = wedding;
//	}

//	@Override
//	public String toString() {
//		return "User [name=" + name + ", email=" + email + ", username=" + username + ", mealChoice=" + mealChoice + ", plusOne=" + plusOne + ", attending=" + attending
//				+ ", userType=" + userType + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(attending, email, id, mealChoice, name, password, plusOne, userType, username, wedding);
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
//		User other = (User) obj;
//		return attending == other.attending && Objects.equals(email, other.email) && Objects.equals(id, other.id)
//				&& Objects.equals(mealChoice, other.mealChoice) && Objects.equals(name, other.name)
//				&& Objects.equals(password, other.password) && plusOne == other.plusOne && userType == other.userType
//				&& Objects.equals(username, other.username) && Objects.equals(wedding, other.wedding);
//	}

}
