package Obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable{

    //members
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private String[] categories;
    private int categoryCount=0;
    private ArrayList<Food> foodList;

    //constrcutor
    public Restaurant(){

    }
    public Restaurant(int id,String name,double score,String price,String zipCode){
        this.id=id;
        this.name=name;
        this.score=score;
        this.price=price;
        this.zipCode=zipCode;
        categories=new String[3];
        foodList=new ArrayList<>();
    }

    //getters
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getScore(){
        return this.score;
    }
    public String getPrice(){
        return this.price;
    }
    public String getZipCode(){
        return this.zipCode;
    }
    public String[] getCategories(){
        return this.categories;
    }
    public int getCategoryCount(){
        return categoryCount;
    }
    public List<Food> getFood(){
        return foodList;
    }

    //setters
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setScore(double score){
        this.score=score;
    }
    public void setPrice(String price){
        this.price=price;
    }
    public void setZipCode(String zipCode){
        this.zipCode=zipCode;
    }

    //adds categories to the restaurant
    //checks-(i) if the category already exists
    public synchronized boolean addCategory(String category){
        if(categoryCount==3){
            return false;
        }
        boolean match=false;
        for(int i=0;i<categoryCount;i++){
            if(categories[i].equalsIgnoreCase(category.trim())){
                match=true;
                break;
            }
        }
        if(!match){
            categories[categoryCount++]=category;
        }
        return !match;
    }

    //removes categories from the restaurant
    //checks-(i) if the category does not exist
    public synchronized boolean removeCategory(String category){
        boolean match=false;
        int matchIndex=-1;
        for(int i=0;i<categoryCount;i++){
            if(categories[i].equalsIgnoreCase(category.trim())){
                match=true;
                matchIndex=i;
                break;
            }
        }
        if(match){
            for(int i=matchIndex;i<categoryCount-1;i++){
                categories[i]=categories[i+1];
            }
            categoryCount--;
        }
        return match;
    }

    //print details
    public void print(){
        System.out.println("Restaurant Id:\t\t"+id);
        System.out.println("Restaurant Name:\t"+name);
        System.out.println("Restaurant Score:\t"+score);
        System.out.println("Restaurantr Price:\t"+price);
        System.out.println("Restaurant Zip Code:\t"+zipCode);
        System.out.println("Restaurant Categories:");
        for(int i=0;i<categoryCount;i++){
            if(!categories[i].equalsIgnoreCase("") && !categories[i].equalsIgnoreCase(null)){
                System.out.println("\t\t\t"+categories[i]);
            }
        }
        System.out.println();
    }

    //search category
    public boolean searchCategory(String category){
        boolean match=false;
        for(int i=0;i<categoryCount;i++){
            if(categories[i].toUpperCase().contains(category.trim().toUpperCase())){
                match=true;
                break;
            }
        }
        return match;
    }

    //search category equal
    public boolean searchCategoryEqual(String category){
        boolean match=false;
        for(int i=0;i<categoryCount;i++){
            if(categories[i].toUpperCase().equals(category.trim().toUpperCase())){
                match=true;
                break;
            }
        }
        return match;
    }

    //add food
    public synchronized void addFood(Food food){
        foodList.add(food);
    }

    //return food count in the restaurant
    public int getCount(){
        return foodList.size();
    }

    //search food by name
    public List<Food> searchFoodByName(String foodName){
        List<Food> tempMenu=new ArrayList<>();
        for(Food food:foodList){
            if(food.getName().toUpperCase().contains(foodName.trim().toUpperCase())){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //seacrh food by category
    public List<Food> searchFoodByCategory(String foodCategory){
        List<Food> tempMenu=new ArrayList<>();
        for(Food food:foodList){
            if(food.getCategory().toUpperCase().contains(foodCategory.trim().toUpperCase())){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //search food by price range
    public List<Food> searchFoodByPriceRange(double lowerPriceRange,double upperPriceRange){
        List<Food> tempMenu=new ArrayList<>();
        for(Food food:foodList){
            if(food.getPrice()<=upperPriceRange&&food.getPrice()>=lowerPriceRange){
                tempMenu.add(food);
            }
        }
        return tempMenu;
    }

    //add food
    public synchronized boolean addFood(String foodName,String category,double price){
        boolean match=false;
        for(Food food:foodList){
            if(food.getName().toUpperCase().equals(foodName.trim().toUpperCase())){
                if(food.getCategory().toUpperCase().equals(category.trim().toUpperCase())){
                    match=true;
                }
            }
        }
        if(!match){
            foodList.add(new Food(id,category,foodName,price));
        }
        return match;
    }
}
