package Server;

import Obj.*;
import Util.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WriteThreadServer implements Runnable{
    Server server;
    SocketWrapper socketWrapper;
    Thread thread;
    List<Restaurant> restaurants;
    List<Food> menu;
    ConcurrentHashMap<String,String> users;
    RestaurantDatabase restaurantDatabase;
    String username;
    HashMap<String,SocketWrapper> sockets;

    public WriteThreadServer(Server server,SocketWrapper socketWrapper,String username,HashMap<String,SocketWrapper> sockets){
        this.server=server;
        this.username=username;
        this.sockets=sockets;
        this.socketWrapper=socketWrapper;
        restaurants=server.getRestaurants();
        menu=server.getMenu();
        users=server.getUsers();
        restaurantDatabase=server.getRestaurantDatabase();
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run(){

            try{
                RestaurantDatabaseDTO restaurantDatabaseDTO=new RestaurantDatabaseDTO();
                restaurantDatabaseDTO.setRestaurantDatabase(restaurantDatabase);
                socketWrapper.write(restaurantDatabaseDTO);
            }catch(Exception e){
                System.out.println(e);}

        }
//        }finally{
//            try{
//                socketWrapper.closeConnection();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
    }

