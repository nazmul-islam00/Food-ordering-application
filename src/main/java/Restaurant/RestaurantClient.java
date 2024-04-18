package Restaurant;

import Obj.Order;
import RestaurantController.AddFoodController;
import RestaurantController.HomeController;
import RestaurantController.LoginController;
import Obj.Restaurant;
import RestaurantController.OrderController;
import User.ReadThread;
import Util.SocketWrapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RestaurantClient extends Application {
    private String username;
    private Stage stage;
    private SocketWrapper socketWrapper;
    private Restaurant restaurant;
    private List<Order> orders;
    private HomeController homeController;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        homeController.updateOrders(orders);
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant=restaurant;
    }

    public Restaurant getRestaurant(){
        return this.restaurant;
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

    @Override
    public void start(Stage stage)throws Exception{
        this.stage=stage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer()throws Exception{
        String serevrAddress="127.0.0.1";
        int serverPort=33333;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter restaurant name:");
        String name=scanner.nextLine();
        setUsername(name);
        socketWrapper=new SocketWrapper(serevrAddress,serverPort);
        socketWrapper.write(name);
        ReadThreadRestaurant readThreadRestaurant=new ReadThreadRestaurant(this,username);
    }

    public void showLoginPage()throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/RestaurantFxml/Login.fxml"));
        Parent root=fxmlLoader.load();
        LoginController loginController=fxmlLoader.getController();
        loginController.setRestaurantClient(this);
        stage.setTitle("Login");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showHomePage(Restaurant restaurant, String username)throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/RestaurantFxml/home.fxml"));
        Parent root=fxmlLoader.load();
        homeController=fxmlLoader.getController();
        homeController.init(username,restaurant,orders);
        if(orders!=null){
            setOrders(orders);
        }
        setUsername(username);
        homeController.setRestaurantClient(this);
        stage.setTitle("Home");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showAddFoodPage()throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/RestaurantFxml/addFood.fxml"));
        Parent root=fxmlLoader.load();
        AddFoodController addFoodController=fxmlLoader.getController();
        addFoodController.init(username,restaurant,orders);

        setUsername(username);
        addFoodController.setRestaurantClient(this);
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
        fxmlLoader.setLocation(getClass().getResource("/RestaurantFxml/orderPage.fxml"));
        Parent root=fxmlLoader.load();
        OrderController orderController=fxmlLoader.getController();
        orderController.init(username,restaurant);
        setUsername(username);
        orderController.setRestaurantClient(this);
        stage.setTitle("Orders");
        File file=new File("src/main/resources/Images/logo.png");
        Image icon=new Image(file.toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showSuccess(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Food Added");
        alert.setHeaderText("Food item added successfully");
        alert.setContentText("New food item added successfully");
        alert.showAndWait();
    }

    public void showFailure(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Information");
        alert.setHeaderText("Invalid Information");
        alert.setContentText("A food item with the same name and category already exists in the restaurant");
        alert.showAndWait();
    }
    public void showAlert(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The restaurant Id and password you provided is not correct");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
