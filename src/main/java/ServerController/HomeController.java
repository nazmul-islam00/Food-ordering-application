package ServerController;

import Server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
public class HomeController {

    @FXML
    private Button addFoodButton;

    @FXML
    private Button addRestaurantButton;

    private Server server;
    @FXML
    void addFood(ActionEvent event) {

    }

    @FXML
    void addRestaurant(ActionEvent event) {

    }

    public void setServer(Server server) {
        this.server=server;
    }
}
