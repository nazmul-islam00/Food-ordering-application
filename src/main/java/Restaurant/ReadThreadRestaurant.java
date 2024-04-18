package Restaurant;

import Obj.Order;
import Obj.Restaurant;
import Util.OrderDTO;
import Util.RestaurantDTO;
import Util.RestaurantDatabaseDTO;
import Util.RestaurantLoginDTO;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class ReadThreadRestaurant implements Runnable{
    private final Thread thread;
    private final RestaurantClient restaurantClient;
    public Restaurant restaurant;
    public String restaurantName;
    private RestaurantDTO restaurantDTO;
    public List<Order> orders=new ArrayList<>();

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public ReadThreadRestaurant(RestaurantClient restaurantClient,String username){
        this.restaurantClient=restaurantClient;
        this.restaurantName=username;
        this.thread=new Thread(this);
        restaurant=new Restaurant();
        thread.start();
    }

    public void run(){
        try{
            while (true){
                Object o=restaurantClient.getSocketWrapper().read();
                if(o!=null){
                    if(o instanceof RestaurantLoginDTO){
                        RestaurantLoginDTO restaurantLoginDTO=(RestaurantLoginDTO) o;
                        System.out.println(restaurantLoginDTO.getRestaurantName());
                        System.out.println(restaurantLoginDTO.isStatus());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(restaurantLoginDTO.isStatus()){
                                    try{
                                        System.out.println("Login successful!");
                                        restaurantClient.setRestaurant(restaurant);
                                        restaurantClient.showHomePage(restaurant,restaurantLoginDTO.getRestaurantName());
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }else{
                                    restaurantClient.showAlert();
                                }
                            }
                        });
                    }else if(o instanceof RestaurantDTO){
                        RestaurantDTO restaurantDTO=(RestaurantDTO) o;
                        restaurant=restaurantDTO.getRestaurant();
                        restaurantName=restaurantDTO.getRestaurantName();
                    }else if(o instanceof OrderDTO){
                        OrderDTO orderDTO=(OrderDTO) o;
                        System.out.println(orderDTO.getOrders().size());
                        for(Order order:orderDTO.getOrders()){
                            System.out.println(order.getFoodName()+" "+order.getQuantity());
                        }
                        List<Order> tempOrderList=orderDTO.getOrders();
                        for(Order order:tempOrderList){
                            boolean match=false;
                            int matchIndex=-1;
                            for(int i=0;i<orders.size();i++){
                                if(orders.get(i).getFoodName().equals(order.getFoodName())){
                                    match=true;
                                    matchIndex=i;
                                    break;
                                }
                            }
                            if(match){
                                int quantity=orders.get(matchIndex).getQuantity();
                                orders.get(matchIndex).setQuantity(quantity+ order.getQuantity());
                            }else{
                                orders.add(order);
                            }
                        }
                        System.out.println(orders.size());
                        restaurantClient.setOrders(orders);
//                        restaurantClient.showHomePage(restaurant,restaurantName);
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                try{
//                                    System.out.println("running");
//                                    restaurantClient.showHomePage(restaurant,restaurantName,orders);
//                                }catch(Exception e){
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
                    }
                    else if(o instanceof RestaurantDatabaseDTO){
                        RestaurantDatabaseDTO restaurantDatabaseDTO=(RestaurantDatabaseDTO) o;
                        System.out.println(restaurantDatabaseDTO.getRestaurantDatabase().getRestaurants().size());
                        System.out.println(restaurantName);
                        restaurant=restaurantDatabaseDTO.getRestaurantDatabase().getSpecificRestaurant(restaurantName);
                        System.out.println(restaurant.getName()+" received");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                restaurantClient.getSocketWrapper().closeConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
