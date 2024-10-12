package com.devesh.project2.foodkart.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private List<Rating> ratings = new ArrayList<>();
    private List<String> pinCodes = new ArrayList<>();
    private FoodItem foodItem;

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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Rating rating) {
        this.ratings.add(rating);
    }

    public List<String> getPinCodes() {
        return pinCodes;
    }

    public void setPinCodes(List<String> pinCodes) {
        this.pinCodes = pinCodes;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public double getRestaurantRating() {
        double finalRating = 0.0;
        for(Rating rating : ratings) {
            finalRating += rating.getValue();
        }
        return finalRating/ratings.size();
    }
}
