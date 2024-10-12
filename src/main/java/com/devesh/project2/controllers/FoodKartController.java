package com.devesh.project2.controllers;

import com.devesh.project2.foodkart.FoodKartDriverService;
import com.devesh.project2.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url/v1")
public class FoodKartController {

    FoodKartDriverService foodKartDriverService = new FoodKartDriverService();

    @Autowired
    private TestService testService;

    @GetMapping("/food-kart")
    public void testFoodKart() throws Exception {
        foodKartDriverService.driver();
    }

    @GetMapping("/test")
    public String test() {
        return testService.callOtherMicroService();
    }


}
