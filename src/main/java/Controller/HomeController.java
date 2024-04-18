package Controller;

import Obj.Food;
import Obj.RestaurantDatabase;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;

public class HomeController {
    @FXML
    private AnchorPane Menu;

    @FXML
    private GridPane MenuGrid;

    @FXML
    private ScrollPane MenuScroll;

    private User user;
    @FXML
    private Label message;
    @FXML
    private ImageView image;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<Food> menuTable;
    @FXML
    private TableColumn<Food, String> categoryColumn;
    @FXML
    private TableColumn<Food, String> nameColumn;

    @FXML
    private TableColumn<Food, Double> priceColumn;

    @FXML
    private TableColumn<Food, Integer> restaurantIdColumn;
    @FXML
    private Button searchFoodButton;

    @FXML
    private Button searchRestaurantButton;
    @FXML
    private Button orderButton;

    public void init(String message, RestaurantDatabase restaurantDatabase){
        this.message.setText("Welcome, "+message);
        ObservableList<Food> foodList= FXCollections.observableArrayList();
        List<Food> menu=restaurantDatabase.getMenu();
        for(Food food:menu){
            foodList.add(food);
        }
        restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<Food,Integer>("restaurantId"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Food,Double>("price"));
        menuTable.setItems(foodList);
    }

    @FXML
    void logoutAction(ActionEvent event){
        try{
            user.showLoginPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(RestaurantDatabase restaurantDatabase){
        menuTable.refresh();
        ObservableList<Food> foodList= FXCollections.observableArrayList();
        List<Food> menu=restaurantDatabase.getMenu();
        for(Food food:menu){
            foodList.add(food);
        }
        restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<Food,Integer>("restaurantId"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Food,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Food,Double>("price"));
        menuTable.setItems(foodList);
    }

    @FXML
    void searchFood(ActionEvent event) {
        try{
            user.showSearchFoodPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchRestaurant(ActionEvent event) {
        try{
            user.showSearchResaturantPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void order(ActionEvent event){
        try{
            user.showOrderPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user=user;
    }
}
