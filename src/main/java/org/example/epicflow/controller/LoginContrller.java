package org.example.epicflow.controller;



public class LoginContrller {

    // 싱글톤
    private LoginContrller(){}
    private static LoginContrller loginContrller = new LoginContrller();
    public static LoginContrller getInstance(){
        return loginContrller;
    }

}
