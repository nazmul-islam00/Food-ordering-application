package Server;

import Obj.Order;
import Obj.Restaurant;
import Obj.RestaurantDatabase;
import Util.*;

import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable{
    private final Thread thread;
    private final SocketWrapper socketWrapper;
    public HashMap<String,String> userMap;
    public HashMap<String,String> restaurantMap;
    public HashMap<String,SocketWrapper> sockets;
    private Server server;
    public ReadThreadServer(HashMap<String,String> userMap,Server server, SocketWrapper socketWrapper, HashMap<String,String> restaurantMap,HashMap<String,SocketWrapper> sockets){
        this.userMap=userMap;
        this.server=server;
        this.restaurantMap=restaurantMap;
        this.socketWrapper=socketWrapper;
        this.sockets=sockets;
        this.thread=new Thread(this);
        thread.start();
    }
    public void run(){
        try{
            while(true){
                Object o=socketWrapper.read();
                if(o!=null){
                    if(o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = "123";
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        socketWrapper.write(loginDTO);
                    }
                    else if(o instanceof RestaurantLoginDTO){
                        RestaurantLoginDTO restaurantLoginDTO=(RestaurantLoginDTO) o;
                        String password=restaurantMap.get(restaurantLoginDTO.getRestaurantName());
                        restaurantLoginDTO.setStatus(restaurantLoginDTO.getPassword().equals(password));
                        socketWrapper.write(restaurantLoginDTO);
                    }
                    else if(o instanceof OrderDTO){
                        System.out.println("Order received");
                        OrderDTO orderDTO=(OrderDTO) o;
                        for(Order order:orderDTO.getOrders()){
                            System.out.println(order.getFoodName()+" "+order.getQuantity());
                        }
                        String restaurantName= orderDTO.getRestaurantName();
                        SocketWrapper socketWrapper1=sockets.get(restaurantName);
                        if(socketWrapper1!=null){
                            try{
                                socketWrapper1.write(orderDTO);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(o instanceof AddFoodDTO){
                        System.out.println("New food added");
                        AddFoodDTO addFoodDTO=(AddFoodDTO) o;
                        System.out.println(addFoodDTO.getFood().getName()+" "+addFoodDTO.getFood().getCategory()+" "+addFoodDTO.getFood().getPrice()+" "+addFoodDTO.getRestaurant().getName());
                        System.out.println(addFoodDTO.getRestaurant().getFood().size());
                        server.update(addFoodDTO.getRestaurant(),addFoodDTO.getFood());
                        System.out.println(server.getMenu().size());
                        RestaurantDatabase restaurantDatabase=new RestaurantDatabase(server.getRestaurants(),server.getMenu());
                        NewRestaurantDatabaseDTO newrestaurantDatabaseDTO=new NewRestaurantDatabaseDTO();
                        newrestaurantDatabaseDTO.setRestaurantDatabase(restaurantDatabase);
                        System.out.println(newrestaurantDatabaseDTO.getRestaurantDatabase().getMenu().size());
                        socketWrapper.write(newrestaurantDatabaseDTO);
//                        List<String> socketlist= (List<String>) sockets.keySet();
//                        for(String s:socketlist){
//                            boolean flag3=false;
//                            for(Restaurant restaurant3:restaurantDatabase.getRestaurants()){
//                                if(s.equals(restaurant3.getName())){
//                                    flag3=true;
//                                }
//                            }
//                            if(!flag3){
//                                sockets.get(s).write(newrestaurantDatabaseDTO);
//                            }
//                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            try{
                socketWrapper.closeConnection();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}