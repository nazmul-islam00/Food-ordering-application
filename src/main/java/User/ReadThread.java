package User;

import Obj.Food;
import Obj.Restaurant;
import Obj.RestaurantDatabase;
import Util.LoginDTO;
import Util.NewRestaurantDatabaseDTO;
import Util.RestaurantDatabaseDTO;
import javafx.application.Platform;

import java.io.IOException;
import java.util.List;

public class ReadThread implements Runnable{
    private final Thread thread;
    private final User user;
    public RestaurantDatabase restaurantDatabase;
    private RestaurantDatabaseDTO restaurantDatabaseDTO;

    public RestaurantDatabase getRestaurantDatabase() {
        return restaurantDatabase;
    }

    public void setRestaurantDatabase(RestaurantDatabase restaurantDatabase) {
        this.restaurantDatabase = restaurantDatabase;
    }

    public ReadThread(User user){
        this.user=user;
        this.thread=new Thread(this);
        restaurantDatabase=new RestaurantDatabase();
        thread.start();;
    }

    public void run(){
        try{
            while(true){
                Object o=user.getSocketWrapper().read();
                if(o!=null){
                    if(o instanceof LoginDTO){
                        LoginDTO loginDTO=(LoginDTO) o;
                        System.out.println(loginDTO.getUsername());
                        System.out.println(loginDTO.isStatus());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(loginDTO.isStatus()){
                                    try{
                                        System.out.println("Login successful!");
                                        user.setRestaurantDatabase(restaurantDatabase);
                                        user.showHomePage(restaurantDatabase,loginDTO.getUsername());
                                        List<Food> menu=restaurantDatabase.getMenu();
                                        System.out.println(menu.size());
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }else{
                                    user.showAlert();
                                }
                            }
                        });
                    } else if (o instanceof  RestaurantDatabaseDTO) {
                        RestaurantDatabaseDTO restaurantDatabaseDTO=(RestaurantDatabaseDTO) o;
                        restaurantDatabase=restaurantDatabaseDTO.getRestaurantDatabase();
//                        user.setRestaurantDatabase(restaurantDatabaseDTO.getRestaurantDatabase());
                    }else if(o instanceof NewRestaurantDatabaseDTO){
                        System.out.println("Received new database");
                        NewRestaurantDatabaseDTO newRestaurantDatabaseDTO=(NewRestaurantDatabaseDTO) o;
                        restaurantDatabase=newRestaurantDatabaseDTO.getRestaurantDatabase();
                        user.setRestaurantDatabase(restaurantDatabase);
                        user.update(restaurantDatabase);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try{
                user.getSocketWrapper().closeConnection();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
