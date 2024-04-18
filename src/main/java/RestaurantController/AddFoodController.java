package RestaurantController;

import Obj.Food;
import Obj.Order;
import Obj.Restaurant;
import Restaurant.RestaurantClient;
import Util.AddFoodDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class AddFoodController {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField restaurantIdField;
    private RestaurantClient restaurantClient;
    private String username;
    private List<Order> orders;
    private Restaurant restaurant;

    @FXML
    void addFood(ActionEvent event) {
        int restaurantid=restaurant.getId();
        String category=categoryField.getText();
        String name=nameField.getText();
        String price=priceField.getText();
        boolean success=false;
        if(!(category.isEmpty()||name.isEmpty()||price.isEmpty())){
            boolean flag=false;
            for(Food f:restaurant.getFood()){
                if(f.getName().toUpperCase().equals(name.toUpperCase().trim())&&f.getCategory().toUpperCase().equals(category.toUpperCase().trim())){
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                try {
                    Food food = new Food(restaurantid, category, name, Double.parseDouble(price));
                    restaurant.addFood(food);
                    success=true;
                    AddFoodDTO addFoodDTO=new AddFoodDTO();
                    addFoodDTO.setFood(food);
                    addFoodDTO.setRestaurant(restaurant);
                    try{
                        restaurantClient.getSocketWrapper().write(addFoodDTO);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }else{
                System.out.println("A food item with the same name and category already exists in the restaurant");
            }
        }
        try{
            restaurantClient.showHomePage(restaurant,username);
            if(orders!=null){
                restaurantClient.setOrders(orders);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(success){
            restaurantClient.showSuccess();
        }else{
            restaurantClient.showFailure();
        }
    }

    @FXML
    void back(ActionEvent event) {
        try{
            restaurantClient.showHomePage(restaurant,username);
            if(orders!=null){
                restaurantClient.setOrders(orders);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setRestaurantClient(RestaurantClient restaurantClient) {
        this.restaurantClient = restaurantClient;
    }

    public RestaurantClient getRestaurantClient() {
        return restaurantClient;
    }

    public void init(String username, Restaurant restaurant, List<Order> orders) {
        this.orders=orders;
        this.username=username;
        this.restaurant=restaurant;
    }
}
