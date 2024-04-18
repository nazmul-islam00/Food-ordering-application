package Util;

        import Obj.RestaurantDatabase;

        import java.io.Serializable;

public class RestaurantDatabaseDTO implements Serializable {
    private RestaurantDatabase restaurantDatabase;
    public RestaurantDatabase getRestaurantDatabase(){
        return restaurantDatabase;
    }
    public void setRestaurantDatabase(RestaurantDatabase restaurantDatabase){
        this.restaurantDatabase=restaurantDatabase;
    }
}
