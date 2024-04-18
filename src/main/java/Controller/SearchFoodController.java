package Controller;

import Obj.RestaurantDatabase;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SearchFoodController {
    private User user;
    @FXML
    private Button costliestFoodButton;

    @FXML
    private Button returnToHomeButton;

    @FXML
    private Button searchByCategoryButton;

    @FXML
    private Button searchByCategoryRButton;

    @FXML
    private Button searchByNameButton;

    @FXML
    private Button searchByNameRButton;

    @FXML
    private Button searchByPriceRangeButton;

    @FXML
    private Button searchByPriceRangeRButton;

    @FXML
    private Button totalFoodItemsButton;
    private RestaurantDatabase restaurantDatabase;
    private String username;

    @FXML
    void costliestFood(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(7);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void returnToHome(ActionEvent event) {
        try{
            user.showHomePage(restaurantDatabase,username);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByCategory(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByCategoryR(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(4);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByName(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByNameR(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByPriceRange(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(5);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByPriceRangeR(ActionEvent event) {
        try{
            user.showSearchFoodResultPage(6);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void totalFoodItems(ActionEvent event) {
        try{
            user.showFoodCountResultPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(String username, RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
    }

    public void setUser(User user) {this.user=user;
    }
}
