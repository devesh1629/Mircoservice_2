package com.devesh.project2.foodkart.services;

import com.devesh.project2.foodkart.models.Order;
import com.devesh.project2.foodkart.models.Rating;
import com.devesh.project2.foodkart.models.Restaurant;
import com.devesh.project2.foodkart.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private static UserService userService;
    private Map<String, User> users = new HashMap<>();
    private User currentUser = null;
    RestaurantService restaurantService = RestaurantService.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public static UserService getInstance() {
        if(userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void registerUser(String name, String gender, String phone, String pincode) {
        if(users.containsKey(phone)) {
            logger.info("User already exists with phone: {}", phone);
            return;
        }
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setPhoneNumber(phone);
        user.setPincode(pincode);
        users.put(phone, user);
        logger.info("New user created with phone: {}", phone);
    }

    public void login(String phone) throws Exception {
        User user = users.get(phone);
        if (user != null) {
            if (currentUser != null && user != currentUser) {
                logoutCurrentUser();
            }
            currentUser = user;
            logger.info("User with phoneNumber {} has been logged in successfully", phone);
        } else {
            throw new Exception("Not a registered User");
        }

    }

    public void logoutCurrentUser() {
        if(currentUser != null) {
            logger.info("User with phoneNumber {} has been logged out successfully", currentUser.getPhoneNumber());
            currentUser = null;
        }
    }

    public void placeOrder(String name, int quantity) throws Exception {
        List<Restaurant> restaurants = restaurantService.getServiceableRestaurants();
        for(Restaurant restaurant : restaurants){
            if(restaurant.getName().equals(name)) {
                if(restaurant.getFoodItem().getCurrentQuantity() >= quantity) {
                    Order order = new Order();
                    order.setQuantity(quantity);
                    order.setRestaurant(restaurant);
                    currentUser.setOrders(order);
                    logger.info("Order placed successfully");
                    restaurant.getFoodItem().setCurrentQuantity(restaurant.getFoodItem().getCurrentQuantity() - quantity);
                } else {
                    throw new Exception("Quantity Not Available");
                }
            }
        }
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            System.out.println("Current user: " + currentUser.getName());
            return currentUser;
        } else {
            System.out.println("No user is currently logged in.");
        }
        return null;
    }

    public void giveReview(String restaurantName, int score, String comment) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        for(Restaurant restaurant : restaurants) {
            if(restaurant.getName().equals(restaurantName)) {
                Rating rating = new Rating();
                rating.setComment(comment);
                rating.setValue(score);
                restaurant.setRatings(rating);
                return;
            }
        }
    }
}
