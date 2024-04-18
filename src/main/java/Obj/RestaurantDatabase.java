package Obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDatabase implements Serializable{

    public List<Restaurant> getRestaurants(){
        return restaurants;
    }

    public List<Food> getMenu(){
        return menu;
    }

    //member collection classes
    private List<Restaurant> restaurants=new ArrayList<>();
    private List<Food> menu=new ArrayList<>();

    //constructor
    public RestaurantDatabase(){

    }
    public RestaurantDatabase(List<Restaurant> restaurants,List<Food> menu){
        this.restaurants=restaurants;
        this.menu=menu;
        for(Food food:menu){
            for(Restaurant restaurant:restaurants){
                if(food.getRestaurantId()==restaurant.getId()){
                    restaurant.addFood(food);
                }
            }
        }
    }

    //search restaurant by name
    //checks-(i) if restaurant doesn't exist
    public List<Restaurant> searchRestaurantByName(String restaurantName){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        if(restaurantName.trim().equals("")){
            return tempRestaurantList;
        }

        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().toUpperCase().contains(restaurantName.trim().toUpperCase())){
                tempRestaurantList.add(restaurant);
            }
        }

        return tempRestaurantList;
    }

    //search restaurant by score range
    public List<Restaurant> searchRestaurantByScore(double lowerScoreRange,double upperScoreRange){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        for(Restaurant restaurant:restaurants){
            if(restaurant.getScore()<=upperScoreRange&&restaurant.getScore()>=lowerScoreRange){
                tempRestaurantList.add(restaurant);
            }
        }

        return tempRestaurantList;
    }

    //search restaurant by category
    public List<Restaurant> searchRestaurantByCategory(String restaurantCategory){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        if(restaurantCategory.trim().equals("")){
            return tempRestaurantList;
        }

        for(Restaurant restaurant:restaurants){
            if(restaurant.searchCategory(restaurantCategory)){
                tempRestaurantList.add(restaurant);
            }
        }

        return tempRestaurantList;
    }

    //search restaurant by category equal
    public List<Restaurant> searchRestaurantByCategoryEqual(String restaurantCategory){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        if(restaurantCategory.trim().equals("")){
            return tempRestaurantList;
        }

        for(Restaurant restaurant:restaurants){
            if(restaurant.searchCategoryEqual(restaurantCategory)){
                tempRestaurantList.add(restaurant);
            }
        }

        return tempRestaurantList;
    }

    //search restaurant by price
    public List<Restaurant> searchRestaurantByPrice(String price){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        if(price.trim().equals("")){
            return tempRestaurantList;
        }

        for(Restaurant restaurant:restaurants){
            if(restaurant.getPrice().equals(price.trim())){
                tempRestaurantList.add(restaurant);
            }
        }
        return tempRestaurantList;
    }

    //search restaurant by zip code
    public List<Restaurant> searchRestaurantByZipCode(String zipCode){
        List<Restaurant> tempRestaurantList=new ArrayList<>();

        if(zipCode.trim().equals("")){
            return tempRestaurantList;
        }

        for(Restaurant restaurant:restaurants){
            if(restaurant.getZipCode().toUpperCase().contains(zipCode.trim().toUpperCase())){
                tempRestaurantList.add(restaurant);
            }
        }

        return tempRestaurantList;
    }

    //get unique category list
    public List<String> uniqueCategoryList(){
        List<String> categoryList=new ArrayList<>();

        for(int i=0;i<restaurants.size();i++){
            String[] tempCategories=restaurants.get(i).getCategories();
            for(int j=0;j<restaurants.get(i).getCategoryCount();j++){

                boolean flag=false;

                //checking if the category has already been added to the list
                for(int k=0;k<categoryList.size();k++){
                    if(categoryList.get(k).equals(tempCategories[j])){
                        flag=true;
                        break;
                    }
                }

                if(!flag && !tempCategories[j].equals("")){
                    categoryList.add(tempCategories[j]);
                }
            }
        }
        return categoryList;
    }

    //search food by name
    public List<Food> searchFoodByName(String foodName){
        List<Food> tempMenu=new ArrayList<>();

        if(foodName.trim().equals("")){
            return tempMenu;
        }
        for(Food food:menu){
            if(food.getName().toUpperCase().contains(foodName.trim().toUpperCase())){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food by name in a restaurant
    //checks-(i) if restaurant does not exist
    public List<Food> searchFoodByNameInARestaurant(String foodName,String restaurantName){
        List<Restaurant> tempRestaurantList=searchRestaurantByName(restaurantName);
        List<Food> tempMenu=new ArrayList<>();
        if(tempRestaurantList.size()==0){
            //tempMenu.add(new Food(-1," ","",-1));
            return tempMenu;
        }
        if(foodName.trim().equals("")){
            return tempMenu;
        }

        for(Restaurant restaurant:tempRestaurantList){
            List<Food> tempFoods=restaurant.searchFoodByName(foodName);
            for(Food food:tempFoods){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food by category
    public List<Food> searchFoodByCategory(String foodCategory){
        List<Food> tempMenu=new ArrayList<>();

        if(foodCategory.trim().equals("")){
            return tempMenu;
        }
        for(Food food:menu){
            if(food.getCategory().toUpperCase().contains(foodCategory.trim().toUpperCase())){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food by category in a restaurant
    //checks-(i) if restaurant does not exist
    public List<Food> searchFoodByCategoryInARestaurant(String foodCategory,String restaurantName){
        List<Restaurant> tempRestaurantList=searchRestaurantByName(restaurantName);
        List<Food> tempMenu=new ArrayList<>();
        if(tempRestaurantList.size()==0){
            //tempMenu.add(new Food(-1," ","",-1));
            return tempMenu;
        }
        if(foodCategory.trim().equals("")){
            return tempMenu;
        }
        for(Restaurant restaurant:tempRestaurantList){
            List<Food> tempFoods=restaurant.searchFoodByCategory(foodCategory);
            for(Food food:tempFoods){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food with price range
    public List<Food> searchFoodByPriceRange(double lowerPriceRange,double upperPriceRange){
        List<Food> tempMenu=new ArrayList<>();

        for(Food food:menu){
            if(food.getPrice()<=upperPriceRange&&food.getPrice()>=lowerPriceRange){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food by price range in a restaurant
    //checks-(i) if restaurant does  not exist
    public List<Food> searchFoodByPriceRangeInARestaurant(double lowerPriceRange,double upperPriceRange,String restaurantName){
        List<Restaurant> tempRestaurantList=searchRestaurantByName(restaurantName);
        List<Food> tempMenu=new ArrayList<>();
        if(tempRestaurantList.size()==0){
            //tempMenu.add(new Food(-1," ","",-1));
            return tempMenu;
        }
        for(Restaurant restaurant:tempRestaurantList){
            List<Food> tempFoods=restaurant.searchFoodByPriceRange(lowerPriceRange, upperPriceRange);
            for(Food food:tempFoods){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search costliest food item(s) on the menu of a restaurant
    public List<Food> searchCostliestFoodInARestaurant(String restaurantName){
        List<Restaurant> tempRestaurantList=searchRestaurantByName(restaurantName);
        List<Food> tempMenu=new ArrayList<>();
        if(tempRestaurantList.size()==0){
            //tempMenu.add(new Food(-1," ","",-1));
            return tempMenu;
        }
        int[] restaurantId=new int[restaurants.size()];
        int matchCount=0;
        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().toUpperCase().contains(restaurantName.trim().toUpperCase())){
                restaurantId[matchCount++]=restaurant.getId();
            }
        }
        double maxPrice=-1;
        for(Food food:menu){
            if(food.getPrice()>maxPrice){
                for(int i=0;i<matchCount;i++){
                    if(food.getRestaurantId()==restaurantId[i]){
                        maxPrice=food.getPrice();
                    }
                }
            }
        }
        for(Food food:menu){
            if(food.getPrice()==maxPrice){
                for(int i=0;i<matchCount;i++){
                    if(food.getRestaurantId()==restaurantId[i]){
                        tempMenu.add(food);
                    }
                }
            }
        }
        return tempMenu;
    }

    //restaurants and total food items on the menu of each
    public int[][] showRestaurantAndTotalFoodItems(){
        int[][] foodItemCount=new int[restaurants.size()][2];

        for(int i=0;i<restaurants.size();i++){
            int restaurantId=restaurants.get(i).getId();
            foodItemCount[i][0]=restaurantId;
            foodItemCount[i][1]=restaurants.get(i).getCount();
        }
        return foodItemCount;
    }

    //get restaurant name from id
    public String getRestaurantNameFromId(int id){
        for(Restaurant restaurant:restaurants){
            if(restaurant.getId()==id){
                return restaurant.getName();
            }
        }
        return null;
    }

    //adds restaurant
    //checks-(i) if restaurant already exists
    public synchronized int addRestaurant(int id,String name,double score,String price,String zipCode,int categoryCount,String[] categories){
        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().toUpperCase().equals(name.trim().toUpperCase())){
                return -1;
            }
            if(restaurant.getId()==id){
                return 0;
            }
        }
        Restaurant tempRestaurant=new Restaurant(id,name.trim(),score,price.trim(),zipCode.trim());
        for(int i=0;i<categoryCount;i++){
            tempRestaurant.addCategory(categories[i].trim());
        }
        restaurants.add(tempRestaurant);
        return 1;
    }

    //adds food item to a restaurant
    //checks-(i) if restaurant does not exist
    public synchronized int addFoodInARestaurant(String restaurantName,String foodName,String category,double price){

        boolean restaurantMatch=false;
        int restaurantId=-1;
        Restaurant temp=new Restaurant(-1,"",-1,"","");

        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().toUpperCase().equals(restaurantName.trim().toUpperCase())){
                restaurantMatch=true;
                restaurantId=restaurant.getId();
                temp=restaurant;
                break;
            }
        }

        if(!restaurantMatch){
            return -1;
        }
        else{
            boolean foodMatch=temp.addFood(foodName.trim(), category.trim(), price);
            if(foodMatch){
                return 0;
            }
            else{
                menu.add(new Food(restaurantId,category.trim(),foodName.trim(),price));
                return 1;
            }
        }
    }

    public Restaurant getSpecificRestaurant(String restaurantName){
        for(Restaurant restaurant:restaurants){
            if(restaurant.getName().equals(restaurantName)){
                return restaurant;
            }
        }
        return new Restaurant();
    }
}
