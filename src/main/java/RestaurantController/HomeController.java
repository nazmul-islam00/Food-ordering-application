package RestaurantController;

import Obj.Food;
import Obj.Order;
import Obj.Restaurant;
import Restaurant.RestaurantClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HomeController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button orderButton;
    private RestaurantClient restaurantClient;
    @FXML
    private Label messageLabel;
    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> foodNameColumn;

    @FXML
    private TableView<Food> menuTable;

    @FXML
    private Label messageLabel1;
    @FXML
    private Button addFoodButton;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> quantityColumn;
    private List<Order> orders;
    private Restaurant restaurant;

    public void updateOrders(List<Order> orders1){
        this.orders=orders1;
        orderTable.refresh();
        ObservableList<Order> orderObservableList=FXCollections.observableArrayList(orders);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderTable.setItems(orderObservableList);
    }

    public void init(String message, Restaurant restaurant, List<Order> orders){
        messageLabel.setText(message);
        this.restaurant=restaurant;
        this.orders=orders;
        List<Food> menu=restaurant.getFood();
        System.out.println(menu.size());
        ObservableList<Food> foodList= FXCollections.observableArrayList(menu);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        menuTable.setItems(foodList);
//        orderTable.refresh();
//        ObservableList<Order> orderObservableList=FXCollections.observableArrayList(orders);
//        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        orderTable.setItems(orderObservableList);
    }

    @FXML
    void logout(ActionEvent event) {
        try{
            restaurantClient.showLoginPage();
        }catch(Exception e){
            e.printStackTrace();;
        }
    }

    @FXML
    void addFood(ActionEvent event){
        try{
            restaurantClient.showAddFoodPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setRestaurantClient(RestaurantClient restaurantClient){
        this.restaurantClient=restaurantClient;
    }
}
