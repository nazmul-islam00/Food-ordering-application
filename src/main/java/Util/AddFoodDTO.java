package Util;

import Obj.Food;
import Obj.Restaurant;

import java.io.Serializable;

public class AddFoodDTO implements Serializable {
    private Food food;
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
