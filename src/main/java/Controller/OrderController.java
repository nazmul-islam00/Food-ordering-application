package Controller;

import Obj.Food;
import Obj.Order;
import Obj.Restaurant;
import Obj.RestaurantDatabase;
import User.User;
import Util.OrderDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    @FXML
    private TableView<Order> cart;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> foodNameColumn;

    @FXML
    private TableView<Food> menuTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Button placeOrderButton;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> quantityColumn;

    @FXML
    private ChoiceBox<String> restaurantChoice;
    private String username;
    private RestaurantDatabase restaurantDatabase;
    private User user;
    @FXML
    private Button returnToHomeButton;
    @FXML
    private Button goButton;
    private List<Order> orderMap;
    @FXML
    private ChoiceBox<String> searchChoice;

    @FXML
    private TextField searchField1;

    @FXML
    private TextField searchField2;

    @FXML
    void go(){
        String restaurantName=restaurantChoice.getValue();
        List<Food> menu=new ArrayList<>();
        if(searchChoice.getValue()=="By Name"){
            String foodName=searchField1.getText();
            if(foodName.isEmpty()){
                foodName=searchField2.getText();
            }
            menu=restaurantDatabase.searchFoodByNameInARestaurant(foodName,restaurantName);
        }else if(searchChoice.getValue()=="By Category"){
            String categoryName=searchField1.getText();
            if(categoryName.isEmpty()){
                categoryName=searchField2.getText();
            }
            menu=restaurantDatabase.searchFoodByCategoryInARestaurant(categoryName,restaurantName);
        }else if(searchChoice.getValue()=="By Price"){
            double price1=Double.parseDouble(searchField1.getText());
            double price2=Double.parseDouble(searchField2.getText());
            menu=(price1>price2)?restaurantDatabase.searchFoodByPriceRangeInARestaurant(price2,price1,restaurantName):restaurantDatabase.searchFoodByPriceRangeInARestaurant(price1,price2,restaurantName);
        }else{
            Restaurant restaurant=restaurantDatabase.getSpecificRestaurant(restaurantName);
            menu=restaurant.getFood();
        }
//        Restaurant restaurant=restaurantDatabase.getSpecificRestaurant(restaurantName);
//        List<Food> menu=restaurant.getFood();
        System.out.println(menu.size());
        ObservableList<Food> foodList= FXCollections.observableArrayList(menu);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        menuTable.setItems(foodList);
        menuTable.setOnMouseClicked(event->{
            Food food=menuTable.getSelectionModel().getSelectedItem();
            Order order=new Order();
            order.setFoodName(food.getName());
            order.setQuantity(1);
            add(order);
        });
        cart.setOnMouseClicked(event->{
            Order order=cart.getSelectionModel().getSelectedItem();
            remove(order);
        });
    }

    private void remove(Order order){
        if(order.getQuantity()>1){
            int index=orderMap.indexOf(order);
            int quantity=orderMap.get(index).getQuantity();
            orderMap.get(index).setQuantity(quantity-1);
        }else{
            orderMap.remove(order);
        }
        cart.refresh();
        ObservableList<Order> orders=FXCollections.observableArrayList(orderMap);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cart.setItems(orders);
    }

    private void add(Order order){
        boolean match=false;
        int matchIndex=-1;
        for(int i=0;i<orderMap.size();i++){
                if(orderMap.get(i).getFoodName().equals(order.getFoodName())){
                    match=true;
                    matchIndex=i;
                    break;
                }
        }
        if(match){
            int quantity=orderMap.get(matchIndex).getQuantity();
            orderMap.get(matchIndex).setQuantity(quantity+1);
        } else{
            orderMap.add(order);
        }
        cart.refresh();
        ObservableList<Order> orders=FXCollections.observableArrayList(orderMap);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cart.setItems(orders);
    }

    @FXML
    void returnToHome(ActionEvent event){
        try{
            user.showHomePage(restaurantDatabase,username);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void placeOrder(ActionEvent event) {
        OrderDTO orderDTO=new OrderDTO();
        System.out.println(orderMap.size());
        orderDTO.setOrders(orderMap);
        orderDTO.setRestaurantName(restaurantChoice.getValue());
        try{
            user.getSocketWrapper().getOos().flush();
            user.getSocketWrapper().write(orderDTO);
            user.getSocketWrapper().getOos().flush();
        }catch(Exception e){
            e.printStackTrace();
        }
        orderMap.clear();
        cart.refresh();
        ObservableList<Order> orders=FXCollections.observableArrayList(orderMap);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cart.setItems(orders);
        try{
            user.showHomePage(restaurantDatabase,username);
        }catch(Exception e){
            e.printStackTrace();
        }
        user.showSuccess();
    }

    public void init(String username, RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase=restaurantDatabase;
        this.username=username;
        orderMap=new ArrayList<>();
        List<Restaurant> restaurants=restaurantDatabase.getRestaurants();
        List<String> restaurantNames=new ArrayList<>();
        for(Restaurant restaurant:restaurants){
                restaurantNames.add(restaurant.getName());
        }
        restaurantChoice.getItems().addAll(restaurantNames);
        List<String> searches=new ArrayList<>();
        searches.add("By Name");
        searches.add("By Category");
        searches.add("By Price");
        searchChoice.getItems().addAll(searches);
    }

    public void setUser(User user) {
        this.user=user;
    }
}
