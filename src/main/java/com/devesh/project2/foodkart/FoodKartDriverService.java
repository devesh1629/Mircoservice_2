package com.devesh.project2.foodkart;

import com.devesh.project2.foodkart.services.RestaurantService;
import com.devesh.project2.foodkart.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class FoodKartDriverService {

    public void driver() throws Exception {
        UserService userService = UserService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();
        userService.registerUser("Pralove", "male", "phoneNumber-1", "HSR");
        userService.registerUser("Nitesh", "male", "phoneNumber-2", "BTM");
        userService.registerUser("Vatsal", "male","phoneNumber-3","BTM");

        userService.login("phoneNumber-1");
        userService.getCurrentUser();
        restaurantService.registerRestaurant("Food Court-1","BTM/HSR","NI Thali",100,5);
        restaurantService.registerRestaurant("Food Court-2","BTM","Burger",120,3);
        userService.login("phoneNumber-2");
        restaurantService.registerRestaurant("Food Court-3","HSR","SI Thali",150,1);

        restaurantService.getServiceableRestaurants();
        userService.placeOrder("Food Court-1",2);
        restaurantService.getAllRestaurants();
        userService.giveReview("Food Court-2",3,"Good Food");
        userService.giveReview("Food Court-1",5,"Nice Food");

    }

}
