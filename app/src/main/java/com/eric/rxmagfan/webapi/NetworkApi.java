package com.eric.rxmagfan.webapi;

/**
 * Created by Eric on 16/5/4.
 */
public class NetworkApi {

    public boolean validateUser(String username,String password) {
        if(username == null || username.length() == 0){
            return false;
        }else {
            return true;
        }
    }

}
