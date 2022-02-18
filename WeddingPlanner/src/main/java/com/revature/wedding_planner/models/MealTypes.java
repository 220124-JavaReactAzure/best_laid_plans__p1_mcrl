package com.revature.wedding_planner.models;

public class MealTypes {
	private int id;
	private String mealType;
	
	public MealTypes() {
		super();
	}

	public MealTypes(int id, String mealType) {
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
	
	
}
