package com.devesh.project2.foodkart.services;

import com.devesh.project2.foodkart.models.FoodItem;
import com.devesh.project2.foodkart.models.Restaurant;
import com.devesh.project2.foodkart.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RestaurantService {

    private Map<String, Restaurant> restaurants = new HashMap<>();

    private static RestaurantService restaurantService;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    public static RestaurantService getInstance() {
        if (restaurantService == null) {
            restaurantService = new RestaurantService();
        }
        return restaurantService;
    }

    public void registerRestaurant(String name, String pinCodes, String foodItemName, double foodItemPrice, int foodItemInitialQuantity) {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(foodItemName);
        foodItem.setPrice(foodItemPrice);
        foodItem.setCurrentQuantity(foodItemInitialQuantity);
        String serviceableAreas[] = pinCodes.split("/");
        List<String> areasList= Arrays.asList(serviceableAreas);

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setFoodItem(foodItem);
        restaurant.setPinCodes(areasList);
        restaurants.put(name, restaurant);
        logger.info("Restaurant successfully registered with name: {}", name);
    }

    public void updateFoodItemQuantity(String name, int quantity) {
        if(restaurants.containsKey(name) == false) {
            logger.info("Restaurant does not exist with name: {}", name);
            return;
        }
        Restaurant restaurant = restaurants.get(name);
        restaurant.getFoodItem().setCurrentQuantity(restaurant.getFoodItem().getCurrentQuantity() + quantity);
        restaurants.put(name, restaurant);
    }

    public void updateLocation(String name, String locations) {
        if(restaurants.containsKey(name) == false) {
            logger.info("Restaurant does not exist with name: {}", name);
            return;
        }
        Restaurant restaurant = restaurants.get(name);
        String serviceableAreas[] = locations.split("/");
        List<String> areasList= Arrays.asList(serviceableAreas);
        restaurant.setPinCodes(areasList);
        restaurants.put(name, restaurant);
    }

    public List<Restaurant> showRestaurantsByOrder(String orderKey) {
        List<Restaurant> serviceableRestaurants = getServiceableRestaurants();
        if(orderKey.equals("Rating")) {
            SortByRating(serviceableRestaurants);
        } else if(orderKey.equals("Price")) {
            SortByPrice(serviceableRestaurants);
        }
        return serviceableRestaurants;
    }

    public void SortByRating(List<Restaurant> restaurants){
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return Double.compare(r2.getRestaurantRating(),r1.getRestaurantRating());
            }
        });
    }

    public void SortByPrice(List<Restaurant> restaurants){
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return Double.compare(r2.getFoodItem().getPrice(),r1.getFoodItem().getPrice());
            }
        });
    }

    public List<Restaurant> getServiceableRestaurants() {
        List<Restaurant> serviceableRestaurants = new ArrayList<>();
        User currentUser = UserService.getInstance().getCurrentUser();
        String userPincode = currentUser.getPincode();

        for(Restaurant restaurant : restaurants.values()) {
            if(restaurant.getPinCodes().contains(userPincode) && restaurant.getFoodItem().getCurrentQuantity()>0)
                serviceableRestaurants.add(restaurant);
        }
        for(Restaurant restaurant : serviceableRestaurants)
            System.out.print(restaurant.getName() + " , ");
        System.out.println();
        return serviceableRestaurants;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants.values().stream().toList();
    }
}
