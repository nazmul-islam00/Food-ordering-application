package Server;

import java.io.*;
import java.net.*;

import Controller.LoginController;
import Obj.*;
import ServerController.HomeController;
import Util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server  {

    private List<Restaurant> restaurants;
    private List<Food> menu;
    private RestaurantDatabase restaurantDatabase;
    private ConcurrentHashMap<String,String> users;
    private volatile int userCount;
    private ServerSocket serverSocket;
    boolean update;
    private HashMap<String,String> userMap;
    private HashMap<String,String> restaurantMap;
    private HashMap<String,SocketWrapper> sockets;
    public Server()throws IOException{
        userMap=new HashMap<>();
        userMap.put("a","123");
        userMap.put("b","123");
        userMap.put("c","123");
        userMap.put("d","123");
        userMap.put("e","123");
        userCount=0;
        update=false;
        restaurants=new ArrayList<>(FileOperations.readRestaurantsFromFile());
        restaurantMap=new HashMap<>();
        for(Restaurant restaurant:restaurants){
            restaurantMap.put(restaurant.getName(),"123");
        }
        sockets=new HashMap<>();
        menu=new ArrayList<>(FileOperations.readMenuFromFile());
        users=new ConcurrentHashMap<>(FileOperations.readUsersFromFile());
        restaurantDatabase=new RestaurantDatabase(restaurants,menu);
        Scanner scanner=new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);

//                System.out.println("Welcome to Admin");
//                System.out.println("1. Add Restaurant");
//                System.out.println("2. Add Food In A Restaurant");
//                System.out.println("3. Exit");
//                int choice=0;
//                try{
//                    choice=scanner.nextInt();
//                    scanner.nextLine();
//                }catch(Exception e){
//                    e.printStackTrace();;
//                }
//                switch(choice){
//                    case 3:{
//                        try{
//                            FileOperations.writeRestaurantsToFile(restaurants);
//                            FileOperations.writeMenuToFile(menu);
//                        }catch(Exception e){
//                            e.printStackTrace();
//                        }
//                        System.out.println("Exiting the system");
//                        return;
//                    }
//                    default:{
//                        System.out.println("Invalid choice");
//                    }
//                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        new Server();
//    }

//    public void showServer(Stage stage)throws Exception{
//        FXMLLoader fxmlLoader=new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/Server/erverHome.fxml"));
//        Parent root=fxmlLoader.load();
//        HomeController homeController=fxmlLoader.getController();
//        homeController.setServer(this);
//        stage.setTitle("Login");
//        File file=new File("src/main/resources/Images/logo.png");
//        Image icon=new Image(file.toURI().toString());
//        stage.getIcons().add(icon);
//        stage.setScene(new Scene(root));
//        stage.setResizable(false);
//        stage.show();
//    }
    public void update(Restaurant restaurant,Food food){
        menu.add(food);
        System.out.println("added "+food.getName());
        for(Restaurant restaurant1:restaurants){
            if(restaurant1.getName().equals(restaurant.getName())){
                restaurant1=restaurant;
                System.out.println("To "+restaurant1.getName());
                System.out.println(restaurant1.getFood().size());
            }
        }
        System.out.println(menu.size());

        try{
            FileOperations.writeMenuToFile(menu);
            FileOperations.writeRestaurantsToFile(restaurants);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            restaurants=FileOperations.readRestaurantsFromFile();
            menu=FileOperations.readMenuFromFile();
            restaurantDatabase=new RestaurantDatabase(restaurants,menu);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws Exception {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String username= (String) socketWrapper.read();
        sockets.put(username,socketWrapper);
        System.out.println(username);
        System.out.println(sockets.size());
        new ReadThreadServer(userMap,this,socketWrapper,restaurantMap,sockets);
        new WriteThreadServer(this,socketWrapper,username,sockets);
    }

    public static void main(String args[])throws IOException {
//        launch(args);
        new Server();
//        System.out.println("Admin");
//        System.out.println("1. Add Restaurant");
//        System.out.println("2. Add Food In A Restaurant");
//        System.out.println("3. Exit");
//        Scanner scanner=new Scanner(System.in);
//        while(true){
//            int choice=scanner.nextInt();
//            scanner.nextLine();
//            switch(choice){
//                case 3:{
//                    RestaurantDatabase restaurantDatabase1=new RestaurantDatabase();
//                }
//            }
//        }
    }


    public int getUserCount(){
        return userCount;
    }
    public List<Restaurant> getRestaurants(){
        return restaurants;
    }
    public List<Food> getMenu(){
        return menu;
    }
    public ConcurrentHashMap<String,String> getUsers(){
        return users;
    }
    public RestaurantDatabase getRestaurantDatabase(){
        return restaurantDatabase;
    }
    public synchronized void increaseUser(){
        userCount++;
    }
    public synchronized void decreaseUser(){
        userCount--;
    }
    public boolean getUpdate(){
        return update;
    }
    public void setUpdate(boolean arg){
        this.update=arg;
    }
}
