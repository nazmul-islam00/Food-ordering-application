package Obj;

import java.io.Serializable;

public class Order implements Serializable {
    private String foodName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setQuantity(int quantiy) {
        this.quantity = quantiy;
    }
}
