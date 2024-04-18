package RestaurantController;

import Restaurant.RestaurantClient;
import Util.RestaurantLoginDTO;
import Util.SocketWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    private RestaurantClient restaurantClient;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button resetButton;

    @FXML
    private TextField restaurantNameField;

    @FXML
    void login(ActionEvent event) {
        String restaurantName=restaurantNameField.getText();
        String password=passwordField.getText();
//        SocketWrapper socketWrapper=restaurantClient.getSocketWrapper();
        RestaurantLoginDTO restaurantLoginDTO=new RestaurantLoginDTO();
        restaurantLoginDTO.setRestaurantName(restaurantName);
        restaurantLoginDTO.setPassword(password);
//        restaurantLoginDTO.setSocketWrapper(socketWrapper);
        try{
            restaurantClient.getSocketWrapper().write(restaurantLoginDTO);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void reset(ActionEvent event) {
        restaurantNameField.setText(null);
        passwordField.setText(null);
    }
    public void setRestaurantClient(RestaurantClient restaurantClient){
        this.restaurantClient=restaurantClient;
    }

}
