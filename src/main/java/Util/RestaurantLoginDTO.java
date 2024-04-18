package Util;

import Obj.Restaurant;

import java.io.Serializable;

public class RestaurantLoginDTO implements Serializable {
    private String restaurantName;
    private String password;
    public boolean status;
//    private SocketWrapper socketWrapper;

//    public void setSocketWrapper(SocketWrapper socketWrapper) {
//        this.socketWrapper = socketWrapper;
//    }
//
//    public SocketWrapper getSocketWrapper() {
//        return socketWrapper;
//    }
//    private Restaurant restaurant;
//
//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

    public boolean isStatus() {
        return status;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPassword() {
        return password;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
