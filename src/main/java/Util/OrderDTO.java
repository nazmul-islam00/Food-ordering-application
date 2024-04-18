package Util;

import java.io.Serializable;
import java.util.List;

import Obj.Order;

public class OrderDTO implements Serializable {
    private List<Order> orders;
    private String restaurantName;

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
