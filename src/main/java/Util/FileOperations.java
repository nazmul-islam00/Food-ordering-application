package Util;

import Obj.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileOperations{

    private static final String RESTAURANT_FILE_NAME="restaurant.txt";
    private static final String MENU_FILE_NAME="menu.txt";
    private static final String USER_FILE_NAME="user.txt";

    public static List<Restaurant> readRestaurantsFromFile() throws IOException{
        //file readers
        BufferedReader restaurantReader=new BufferedReader(new FileReader(RESTAURANT_FILE_NAME));

        //collection class for storing restaurants from the input file
        final List<Restaurant> inputRestaurant=new ArrayList<>();

        //reading data from the restaurant file
        while(true){
            String restaurantString=restaurantReader.readLine();
            if(restaurantString==null) break;
            String[] restaurantParameters=restaurantString.split(",",-1);

            Restaurant tempRestaurant=new Restaurant(Integer.parseInt(restaurantParameters[0]),restaurantParameters[1],Double.parseDouble(restaurantParameters[2]),restaurantParameters[3],restaurantParameters[4]);

            for(int i=5;i<=7;i++){
                if(restaurantParameters[i].equals(""))
                    continue;
                tempRestaurant.addCategory(restaurantParameters[i]);
            }

            inputRestaurant.add(tempRestaurant);
        }
        restaurantReader.close();
        return inputRestaurant;
    }

    public static void writeRestaurantsToFile(List<Restaurant> inputRestaurant) throws IOException{

        BufferedWriter RestaurantWriter=new BufferedWriter(new FileWriter(RESTAURANT_FILE_NAME));
        for(Restaurant restaurant:inputRestaurant){
            RestaurantWriter.write(restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZipCode());
            for(int i=0;i<restaurant.getCategoryCount();i++){
                RestaurantWriter.write(","+restaurant.getCategories()[i]);
            }
            for(int i=0;i<3-restaurant.getCategoryCount();i++){
                RestaurantWriter.write(",");
            }
            RestaurantWriter.write(System.lineSeparator());
        }
        RestaurantWriter.close();
    }

    public static List<Food> readMenuFromFile() throws IOException{

        BufferedReader menuReader=new BufferedReader(new FileReader(MENU_FILE_NAME));
        //collection class for storing foods from the menu file
        final List<Food> inputMenu=new ArrayList<>();

        //reading data from the menu file
        while(true){
            String menuString=menuReader.readLine();
            if(menuString==null) break;
            String[] menuParameters=menuString.split(",",-1);

            inputMenu.add(new Food(Integer.parseInt(menuParameters[0]),menuParameters[1],menuParameters[2],Double.parseDouble(menuParameters[3])));
        }
        menuReader.close();
        return inputMenu;
    }

    public static void writeMenuToFile(List<Food> inputMenu)throws IOException{

        BufferedWriter MenuWriter=new BufferedWriter(new FileWriter(MENU_FILE_NAME));
        for(Food food:inputMenu){
            MenuWriter.write(food.getRestaurantId()+","+food.getCategory()+","+food.getName()+","+food.getPrice());
            MenuWriter.write(System.lineSeparator());
        }
        MenuWriter.close();
    }

    public static ConcurrentHashMap<String,String> readUsersFromFile()throws IOException{

        BufferedReader userReader=new BufferedReader(new FileReader(USER_FILE_NAME));
        ConcurrentHashMap<String,String> userList=new ConcurrentHashMap<>();
        while(true){
            String userString=userReader.readLine();
            if(userString==null) break;
            String[] userParameters=userString.split(",",-1);
            String username=userParameters[0];
            String password=userParameters[1];
            if(username.length()>0){
                userList.put(username,password);
            }
        }
        userReader.close();
        return userList;
    }

    public static void writeUserToFile(ConcurrentHashMap<String,String> userList)throws IOException{

        BufferedWriter userWriter=new BufferedWriter(new FileWriter(USER_FILE_NAME));
        for(String username:userList.keySet()){
            String userParameters=username+","+userList.get(username);
            userWriter.write(userParameters);
            userWriter.write(System.lineSeparator());
        }
        userWriter.close();
    }
}