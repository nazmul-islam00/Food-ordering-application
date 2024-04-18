package Util;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    private String username;
    private String password;
    public boolean status;
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status){
        this.status=status;
    }
}
