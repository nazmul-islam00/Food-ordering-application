package RestaurantController;

import Obj.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Restaurant.RestaurantClient;

public class OrderController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> foodNameColumn;

    @FXML
    private TableView<?> orderTable;

    @FXML
    private TableColumn<?, ?> quantityColumn;
    private String username;
    private RestaurantClient restaurantClient;
    private Restaurant restaurant;

    @FXML
    void back(ActionEvent event) {
        try{
//            restaurantClient.showHomePage(restaurant,username);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(String username, Restaurant restaurant) {
        this.restaurant=restaurant;
        this.username=username;
    }

    public void setRestaurantClient(RestaurantClient restaurantClient) {
        this.restaurantClient=restaurantClient;
    }
}
