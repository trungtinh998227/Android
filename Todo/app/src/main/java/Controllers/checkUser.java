package Controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Models.User;

public class checkUser {
    private List<User> listUser;

    public checkUser(List<User> listUser) {
        this.listUser = listUser;
    }
    public boolean checkUserName(String username){
        for (int i= 0; i<listUser.size();i++){
            if(username.equals(listUser.get(i).getUserName())){
                return true;
            }
        }
        return false;
    }
    public boolean checkPass(String pass){
        for (int i= 0; i<listUser.size();i++){
            if(pass.equals(listUser.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
}
