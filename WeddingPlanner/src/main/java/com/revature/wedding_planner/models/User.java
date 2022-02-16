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
	@Column(name="user_fname")
	private String fname;
	@Column(name="user_lname")
	private String lname;
	@Column(name="user_email", unique = true, nullable = false)
	private String email;
	@Column(name="user_phone_number")
	private String phoneNumber;
	@Column(name="user_username")
	private String username;
	@Column(name="user_password")
	private String password;
	@Column(name="user_meal_choice")
	private String mealChoice;
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

	public User(String id, String fname, String lname, String email, String username, String password, int userType) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getMealChoice() {
		return mealChoice;
	}

	public void setMealChoice(String mealChoice) {
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
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", username=" + username + ", password=" + password + ", mealChoice=" + mealChoice
				+ ", plusOne=" + plusOne + ", attending=" + attending + ", userType=" + userType + ", weddingId="
				+ weddingId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(attending, email, fname, id, lname, mealChoice, password, phoneNumber, plusOne, userType,
				username, weddingId);
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
		return attending == other.attending && Objects.equals(email, other.email) && Objects.equals(fname, other.fname)
				&& Objects.equals(id, other.id) && Objects.equals(lname, other.lname)
				&& Objects.equals(mealChoice, other.mealChoice) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && plusOne == other.plusOne
				&& userType == other.userType && Objects.equals(username, other.username)
				&& Objects.equals(weddingId, other.weddingId);
	}

	
	
	
	
	
}
