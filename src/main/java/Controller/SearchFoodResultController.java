package Controller;

import Obj.Food;
import Obj.RestaurantDatabase;
import User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SearchFoodResultController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TextField prompt1;

    @FXML
    private TextField prompt2;

    @FXML
    private TextField prompt3;

    @FXML
    private TableColumn<?, ?> restaurantIdColumn;

    @FXML
    private Label resultLabel;

    @FXML
    private TableView<Food> resultTable;

    @FXML
    private Button searchButton;
    private User user;
    private int status;
    private RestaurantDatabase restaurantDatabase;
    private String username;

    @FXML
    void back(ActionEvent event) {
        try{
            System.out.println(status);
            user.showSearchFoodPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        switch (status){
            case 1:{
                String name=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByName(name);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 2:{
                String restaurantName=prompt2.getText();
                String foodName=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByNameInARestaurant(foodName,restaurantName);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 3:{
                String category=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByCategory(category);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 4:{
                String restaurantName=prompt2.getText();
                String category=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByCategoryInARestaurant(category,restaurantName);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 5:{
                String upperRange=prompt2.getText();
                String lowerRange=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByPriceRange(Double.parseDouble(lowerRange),Double.parseDouble(upperRange));
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 6:{
                String restaurantName=prompt1.getText();
                String upperRange=prompt2.getText();
                String lowerRange=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchFoodByPriceRangeInARestaurant(Double.parseDouble(lowerRange),Double.parseDouble(upperRange),restaurantName);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
            case 7:{
                String restaurantName=prompt3.getText();
                List<Food> tempMenu=restaurantDatabase.searchCostliestFoodInARestaurant(restaurantName);
                resultLabel.setText(tempMenu.size()+" search "+((tempMenu.size()<=1)?"result":"results"));
                ObservableList<Food> foodList=FXCollections.observableArrayList(tempMenu);
                restaurantIdColumn.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                resultTable.setItems(foodList);
                break;
            }
        }
    }

    public void init(String username, RestaurantDatabase restaurantDatabase, int status) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
        this.status=status;
        switch(status){
            case 1:{
                prompt1.setVisible(false);
                prompt2.setVisible(false);
                prompt3.setPromptText("Enter Name");
                break;
            }
            case 2:{
                prompt1.setVisible(false);
                prompt3.setPromptText("Enter Food Name");
                prompt2.setPromptText("Enter Restaurant Name");
                break;
            }
            case 3:{
                prompt1.setVisible(false);
                prompt2.setVisible(false);
                prompt3.setPromptText("Enter Category");
                break;
            }
            case 4:{
                prompt1.setVisible(false);
                prompt3.setPromptText("Enter Category");
                prompt2.setPromptText("Enter Restaurant Name");
                break;
            }
            case 5:{
                prompt1.setVisible(false);
                prompt2.setPromptText("Enter Upper Range");
                prompt3.setPromptText("Enter Lower Range");
                break;
            }
            case 6:{
                prompt1.setPromptText("Enter Restaurant Name");
                prompt2.setPromptText("Enter Upper Range");
                prompt3.setPromptText("Enter Lower Range");
                break;
            }
            case 7:{
                prompt1.setVisible(false);
                prompt2.setVisible(false);
                prompt3.setPromptText("Enter Restaurant Name");
                break;
            }
        }
    }

    public void setUser(User user) {
        this.user=user;
    }
}
