package Obj;

import java.io.Serializable;

public class Food implements Serializable {

    //member variables
    private int restaurantId;
    private String category;
    private String name;
    private double price;

    //constructor
    public Food(int restaurantId,String category,String name,double price){
        this.restaurantId=restaurantId;
        this.category=category;
        this.name=name;
        this.price=price;
    }

    //getters
    public int getRestaurantId(){
        return this.restaurantId;
    }
    public String getCategory(){
        return this.category;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }

    //setters
    public void setRestaurantId(int restaurantId){
        this.restaurantId=restaurantId;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPrice(double price){
        this.price=price;
    }

    //print details
    public void print(){
        System.out.println("Restaurant Id:\t"+restaurantId);
        System.out.println("Food Category:\t"+category);
        System.out.println("Food Name:\t"+name);
        System.out.println("Food Price:\t$"+price);
        System.out.println();
    }
}
