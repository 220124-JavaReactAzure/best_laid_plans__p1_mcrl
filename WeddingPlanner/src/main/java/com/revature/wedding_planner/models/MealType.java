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
@Table(name="meal_types")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MealType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="meal_type_id")
	private int id;
	
	@Column(name = "meal_type", unique = true, nullable = false)
	private String mealType;
	

	@OneToMany(mappedBy="mealChoice", fetch=FetchType.EAGER)
	@JsonIgnoreProperties(value="mealChoice")
	private List<User> users;
	
	public MealType() {
		super();
	}

	public MealType(String mealType) {
		super();
		this.mealType = mealType;
	}

	public MealType(int id, String mealType) {
		super();
		this.id = id;
		this.mealType = mealType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	@Override
	public String toString() {
		return "[" + mealType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mealType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealType other = (MealType) obj;
		return id == other.id && Objects.equals(mealType, other.mealType);
	}
	
	
}
