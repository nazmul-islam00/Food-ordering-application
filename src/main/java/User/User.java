package User;

import Controller.*;
import Obj.Food;
import Obj.Order;
import Obj.Restaurant;
import Obj.RestaurantDatabase;
import Server.ReadThreadServer;
import Util.RestaurantDatabaseDTO;
import Util.SocketWrapper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class User extends Application {

    private String username;
    private Stage stage;
    private SocketWrapper socketWrapper;
    private RestaurantDatabase restaurantDatabase;

    public void setRestaurantDatabase(RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase = restaurantDatabase;
    }

    public void update(RestaurantDatabase restaurantDatabase){
        this.restaurantDatabase=restaurantDatabase;
        System.out.println(this.restaurantDatabase.getMenu().size());
        homeController.update(restaurantDatabase);
    }

    public RestaurantDatabase getRestaurantDatabase() {
        return restaurantDatabase;
    }

    public Stage getStage(){
        return stage;
    }
    public SocketWrapper getSocketWrapper(){
        return socketWrapper;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    private HomeController homeController;
    private OrderController orderController;
    private SearchRestaurantController searchRestaurantController;
    private SearchFoodController searchFoodController;
    private SearchFoodResultController searchFoodResultController;
    private SearchRestaurantResultController searchRestaurantResultController;
    private FoodCountResultController FoodCountResultController;

    @Override
    public void start(Stage stage)throws Exception{
        this.stage=stage;
        connectToServer();
        showLoginPage();

    }

    private void connectToServer() throws Exception{
        String serevrAddress="127.0.0.1";
        int serverPort=33333;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter name:");
        String name=scanner.nextLine();
        socketWrapper=new SocketWrapper(serevrAddress,serverPort);
        socketWrapper.write(name);
        ReadThread readThread=new ReadThread(this);
    }

    public void showLoginPage() throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/Login.fxml"));
        Parent root=fxmlLoader.load();
        LoginController loginController=fxmlLoader.getController();
        loginController.setUser(this);
        stage.setTitle("Login");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showHomePage(RestaurantDatabase restaurantDatabase,String username)throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/home.fxml"));
        Parent root=fxmlLoader.load();
        /*HomeController*/ homeController=fxmlLoader.getController();
        homeController.init(username,restaurantDatabase);
        setUsername(username);
        homeController.setUser(this);
        stage.setTitle("Home");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showOrderPage()throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/orderPage.fxml"));
        Parent root=fxmlLoader.load();
        /*OrderController*/ orderController=fxmlLoader.getController();
        orderController.init(username,restaurantDatabase);
        orderController.setUser(this);
        stage.setTitle("Order");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showSearchResaturantPage() throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/searchRestaurant.fxml"));
        Parent root=fxmlLoader.load();
        /*SearchRestaurantController*/ searchRestaurantController=fxmlLoader.getController();
        searchRestaurantController.init(username,restaurantDatabase);
        searchRestaurantController.setUser(this);
        stage.setTitle("Home");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showSearchFoodPage()throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/searchFood.fxml"));
        Parent root=fxmlLoader.load();
        /*SearchFoodController*/ searchFoodController=fxmlLoader.getController();
        searchFoodController.init(username,restaurantDatabase);
        searchFoodController.setUser(this);
        stage.setTitle("Search Food");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showSearchRestaurantResultPage(int status) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/searchRestaurantResult.fxml"));
        Parent root=fxmlLoader.load();
        /*SearchRestaurantResultController*/ searchRestaurantResultController=fxmlLoader.getController();
        searchRestaurantResultController.init(username,restaurantDatabase,status);
        searchRestaurantResultController.setUser(this);
        stage.setTitle("Search Restaurant");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showSearchFoodResultPage(int status) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/searchFoodResult.fxml"));
        Parent root=fxmlLoader.load();
        /*SearchFoodResultController*/ searchFoodResultController=fxmlLoader.getController();
        searchFoodResultController.init(username,restaurantDatabase,status);
        searchFoodResultController.setUser(this);
        stage.setTitle("Search Food");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showFoodCountResultPage() throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Fxml/foodCountResult.fxml"));
        Parent root=fxmlLoader.load();
        /*FoodCountResultController*/ FoodCountResultController=fxmlLoader.getController();
        FoodCountResultController.init(username,restaurantDatabase);
        FoodCountResultController.setUser(this);
        stage.setTitle("Search Food");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    public void showAlert(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct");
        alert.showAndWait();
    }

    public void showSuccess(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Order Placed!");
        alert.setContentText("Your order has been successfully placed");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
