package Controller;

import User.User;
import Util.LoginDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    private User user;
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button resetButton;
    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event){
        String username=userText.getText();
        String password=passwordText.getText();
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        try{
            user.getSocketWrapper().write(loginDTO);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event){
        userText.setText(null);
        passwordText.setText(null);
    }

    public void setUser(User user){
        this.user=user;
    }

}
