package Controller;

import Obj.Restaurant;
import Obj.RestaurantDatabase;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;

public class SearchRestaurantController {
    private User user;

    @FXML
    private Button categoryWiseListButton;

    @FXML
    private Button returnToHomeButton;

    @FXML
    private Button searchByCategoryButton;

    @FXML
    private Button searchByNameButton;

    @FXML
    private Button searchByPriceButton;

    @FXML
    private Button searchByScoreButton;

    @FXML
    private Button searchByZipCodeButton;
    private RestaurantDatabase restaurantDatabase;
    private String username;
    private int status;

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
            user.showSearchRestaurantResultPage(3);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByName(ActionEvent event) {
        try{
            user.showSearchRestaurantResultPage(1);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByPrice(ActionEvent event) {
        try{
            user.showSearchRestaurantResultPage(4);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByScore(ActionEvent event) {
        try{
            user.showSearchRestaurantResultPage(2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void searchByZipCode(ActionEvent event) {
        try{
            user.showSearchRestaurantResultPage(5);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void categoryWiseList(ActionEvent event) {
        try{
            user.showSearchRestaurantResultPage(6);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(String username,RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
    }

    public void setUser(User user) {
        this.user=user;
    }
}
