package com.devesh.project2.foodkart.models;

public class RestaurantOwner {

    private Restaurant restaurant;
    private String id;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
