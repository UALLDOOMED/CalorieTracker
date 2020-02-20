package com.example.assignmentapp;

public class Food {
    private Integer foodid;
    private String foodName;
    private String category;
    private Integer calorieAmount;
    private String servingUnit;
    private Double servingAmount;
    private Integer fat;
    public Food(){

    }
    public Food(Integer foodid) {
        this.foodid = foodid;
    }

    public Food(Integer foodid,String foodName,String category, Integer calorie, String unit,Double amount, Integer fat) {
        this.foodid = foodid;
        this.foodName = foodName;
        this.category = category;
        this.calorieAmount = calorie;
        this.servingUnit = unit;
        this.servingAmount = amount;
        this.fat = fat;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(Integer calorieAmount) {
        this.calorieAmount = calorieAmount;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public Double getServingAmount() {
        return servingAmount;
    }

    public void setServingAmount(Double servingAmount) {
        this.servingAmount = servingAmount;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }
}
