package Obj;

public class FoodCount {
    private String restaurantName;
    private int foodCount;
    public FoodCount(String restaurantName, int foodCount){
        this.restaurantName=restaurantName;
        this.foodCount=foodCount;
    }
    public void setRestaurantName(String restaurantName){
        this.restaurantName=restaurantName;
    }
    public void setFoodCount(int foodCount){
        this.foodCount=foodCount;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getFoodCount() {
        return foodCount;
    }
}
