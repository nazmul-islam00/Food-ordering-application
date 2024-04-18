package Controller;

import Obj.Restaurant;
import Obj.RestaurantDatabase;
import User.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SearchRestaurantResultController {

    @FXML
    private Button backButton;


    @FXML
    private TextField prompt2;
    @FXML
    private TextField prompt1;

    @FXML
    private Label resultLabel;

    @FXML
    private TreeView<String> resultTable;

    @FXML
    private Button searchButton;
    private User user;
    private RestaurantDatabase restaurantDatabase;
    private String username;
    private int status;

    @FXML
    void back(ActionEvent event) {
        try{
            System.out.println(status);
            user.showSearchResaturantPage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        switch (status){
            case 1:{
                String name=prompt2.getText();
                List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByName(name);
                resultLabel.setText(tempRestaurantList.size()+" search "+((tempRestaurantList.size()<=1)?"result":"results"));
                if(tempRestaurantList.size()==0){
                    break;
                }
                TreeItem<String> rootItem=new TreeItem<>("Results");
                for(Restaurant restaurant:tempRestaurantList){
                    TreeItem<String> restaurantName=new TreeItem<>(restaurant.getName());
                    TreeItem<String> restaurantId=new TreeItem<>("Id: "+restaurant.getId());
                    TreeItem<String> restaurantScore=new TreeItem<>("Score: "+restaurant.getScore());
                    TreeItem<String> restaurantZipCode=new TreeItem<>("Zip Code: "+restaurant.getZipCode());
                    TreeItem<String> restaurantPrice=new TreeItem<>("Price: "+restaurant.getPrice());
                    TreeItem<String> restaurantCategoryList=new TreeItem<>("Categories");
                    for(String category:restaurant.getCategories()){
                        TreeItem<String> restaurantCategory=new TreeItem<>(category);
                        restaurantCategoryList.getChildren().add(restaurantCategory);
                    }
                    restaurantName.getChildren().addAll(restaurantId,restaurantScore,restaurantPrice,restaurantZipCode,restaurantCategoryList);
                    rootItem.getChildren().add(restaurantName);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
            case 2:{
                String score1=prompt1.getText();
                String score2=prompt2.getText();
                if(score1==null || score2==null){
                    break;
                }
                List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByScore(Double.parseDouble(score1),Double.parseDouble(score2));
                resultLabel.setText(tempRestaurantList.size()+" search "+((tempRestaurantList.size()<=1)?"result":"results"));
                if(tempRestaurantList.size()==0){
                    break;
                }
                TreeItem<String> rootItem=new TreeItem<>("Results");
                for(Restaurant restaurant:tempRestaurantList){
                    TreeItem<String> restaurantName=new TreeItem<>(restaurant.getName());
                    TreeItem<String> restaurantId=new TreeItem<>("Id: "+restaurant.getId());
                    TreeItem<String> restaurantScore=new TreeItem<>("Score: "+restaurant.getScore());
                    TreeItem<String> restaurantZipCode=new TreeItem<>("Zip Code: "+restaurant.getZipCode());
                    TreeItem<String> restaurantPrice=new TreeItem<>("Price: "+restaurant.getPrice());
                    TreeItem<String> restaurantCategoryList=new TreeItem<>("Categories");
                    for(String category:restaurant.getCategories()){
                        TreeItem<String> restaurantCategory=new TreeItem<>(category);
                        restaurantCategoryList.getChildren().add(restaurantCategory);
                    }
                    restaurantName.getChildren().addAll(restaurantId,restaurantScore,restaurantPrice,restaurantZipCode,restaurantCategoryList);
                    rootItem.getChildren().add(restaurantName);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
            case 3:{
                String categoryName=prompt2.getText();
                List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByCategory(categoryName);
                resultLabel.setText(tempRestaurantList.size()+" search "+((tempRestaurantList.size()<=1)?"result":"results"));
                if(tempRestaurantList.size()==0){
                    break;
                }
                TreeItem<String> rootItem=new TreeItem<>("Results");
                for(Restaurant restaurant:tempRestaurantList){
                    TreeItem<String> restaurantName=new TreeItem<>(restaurant.getName());
                    TreeItem<String> restaurantId=new TreeItem<>("Id: "+restaurant.getId());
                    TreeItem<String> restaurantScore=new TreeItem<>("Score: "+restaurant.getScore());
                    TreeItem<String> restaurantZipCode=new TreeItem<>("Zip Code: "+restaurant.getZipCode());
                    TreeItem<String> restaurantPrice=new TreeItem<>("Price: "+restaurant.getPrice());
                    TreeItem<String> restaurantCategoryList=new TreeItem<>("Categories");
                    for(String category:restaurant.getCategories()){
                        TreeItem<String> restaurantCategory=new TreeItem<>(category);
                        restaurantCategoryList.getChildren().add(restaurantCategory);
                    }
                    restaurantName.getChildren().addAll(restaurantId,restaurantScore,restaurantPrice,restaurantZipCode,restaurantCategoryList);
                    rootItem.getChildren().add(restaurantName);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
            case 4:{
                String price=prompt2.getText();
                List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByPrice(price);
                resultLabel.setText(tempRestaurantList.size()+" search "+((tempRestaurantList.size()<=1)?"result":"results"));
                if(tempRestaurantList.size()==0){
                    break;
                }
                TreeItem<String> rootItem=new TreeItem<>("Results");
                for(Restaurant restaurant:tempRestaurantList){
                    TreeItem<String> restaurantName=new TreeItem<>(restaurant.getName());
                    TreeItem<String> restaurantId=new TreeItem<>("Id: "+restaurant.getId());
                    TreeItem<String> restaurantScore=new TreeItem<>("Score: "+restaurant.getScore());
                    TreeItem<String> restaurantZipCode=new TreeItem<>("Zip Code: "+restaurant.getZipCode());
                    TreeItem<String> restaurantPrice=new TreeItem<>("Price: "+restaurant.getPrice());
                    TreeItem<String> restaurantCategoryList=new TreeItem<>("Categories");
                    for(String category:restaurant.getCategories()){
                        TreeItem<String> restaurantCategory=new TreeItem<>(category);
                        restaurantCategoryList.getChildren().add(restaurantCategory);
                    }
                    restaurantName.getChildren().addAll(restaurantId,restaurantScore,restaurantPrice,restaurantZipCode,restaurantCategoryList);
                    rootItem.getChildren().add(restaurantName);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
            case 5:{
                String zipCode=prompt2.getText();
                List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByZipCode(zipCode);
                resultLabel.setText(tempRestaurantList.size()+" search "+((tempRestaurantList.size()<=1)?"result":"results"));
                if(tempRestaurantList.size()==0){
                    break;
                }
                TreeItem<String> rootItem=new TreeItem<>("Results");
                for(Restaurant restaurant:tempRestaurantList){
                    TreeItem<String> restaurantName=new TreeItem<>(restaurant.getName());
                    TreeItem<String> restaurantId=new TreeItem<>("Id: "+restaurant.getId());
                    TreeItem<String> restaurantScore=new TreeItem<>("Score: "+restaurant.getScore());
                    TreeItem<String> restaurantZipCode=new TreeItem<>("Zip Code: "+restaurant.getZipCode());
                    TreeItem<String> restaurantPrice=new TreeItem<>("Price: "+restaurant.getPrice());
                    TreeItem<String> restaurantCategoryList=new TreeItem<>("Categories");
                    for(String category:restaurant.getCategories()){
                        TreeItem<String> restaurantCategory=new TreeItem<>(category);
                        restaurantCategoryList.getChildren().add(restaurantCategory);
                    }
                    restaurantName.getChildren().addAll(restaurantId,restaurantScore,restaurantPrice,restaurantZipCode,restaurantCategoryList);
                    rootItem.getChildren().add(restaurantName);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
        }
    }

    public void setUser(User user){
        this.user=user;
    }

    public void init(String username, RestaurantDatabase restaurantDatabase,int status) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
        this.status=status;
        switch (status){
            case 1:{
                prompt1.setVisible(false);
                prompt2.setPromptText("Enter Name");
                break;
            }
            case 2:{
                prompt2.setPromptText("Enter Upper Range");
                prompt1.setPromptText("Enter Lower Range");
                break;
            }
            case 3:{
                prompt1.setVisible(false);
                prompt2.setPromptText("Enter Category");
                break;
            }
            case 4:{
                prompt1.setVisible(false);
                prompt2.setPromptText("Enter Price");
                break;
            }
            case 5:{
                prompt1.setVisible(false);
                prompt2.setPromptText("Enter Zip Code");
                break;
            }
            case 6:{
                prompt1.setVisible(false);
                prompt2.setVisible(false);
                searchButton.setVisible(false);
                TreeItem<String> rootItem=new TreeItem<>("Results");
                List<String> uniqueCategoryList=restaurantDatabase.uniqueCategoryList();
                for(String category:uniqueCategoryList){
                    TreeItem<String> categoryItem=new TreeItem<>(category);
                    List<Restaurant> tempRestaurantList=restaurantDatabase.searchRestaurantByCategoryEqual(category);
                    for(int i=0;i<tempRestaurantList.size();i++){
                        TreeItem<String> restaurant=new TreeItem<>(tempRestaurantList.get(i).getName());
                        categoryItem.getChildren().add(restaurant);
                    }
                    rootItem.getChildren().add(categoryItem);
                }
                resultTable.setShowRoot(false);
                resultTable.setRoot(rootItem);
                break;
            }
        }
    }
}
