package Util;

import Obj.Restaurant;

import java.io.Serializable;

public class RestaurantDTO implements Serializable {
    private Restaurant restaurant;
    private String restaurantName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
