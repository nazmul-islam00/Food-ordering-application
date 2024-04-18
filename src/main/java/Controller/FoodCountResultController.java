package Controller;

import Obj.FoodCount;
import Obj.RestaurantDatabase;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FoodCountResultController {

    @FXML
    private Button backButton;
    @FXML
    private TableView<FoodCount> countTable;

    @FXML
    private TableColumn<?, ?> foodCountColumn;

    @FXML
    private TableColumn<FoodCount, String> restaurantNameColumn;
    private User user;
    private RestaurantDatabase restaurantDatabase;
    private String username;

    @FXML
    void back(ActionEvent event) {
        try{
            user.showSearchFoodPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(String username, RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
        ObservableList<FoodCount> foodCountList= FXCollections.observableArrayList();
        int[][] tempCountList=restaurantDatabase.showRestaurantAndTotalFoodItems();
        for(int i=0;i<tempCountList.length;i++){
            foodCountList.add(new FoodCount(restaurantDatabase.getRestaurantNameFromId(tempCountList[i][0]),tempCountList[i][1]));
        }
        restaurantNameColumn.setCellValueFactory(new PropertyValueFactory<FoodCount,String>("restaurantName"));
        foodCountColumn.setCellValueFactory(new PropertyValueFactory<>("foodCount"));
        countTable.setItems(foodCountList);
    }

    public void setUser(User user) {
        this.user=user;
    }
}
