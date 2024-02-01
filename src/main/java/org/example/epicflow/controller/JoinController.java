package org.example.epicflow.controller;    // 패키지명


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JoinController {   // Class Start

@FXML Button close;

    public void closeda(){
        try {
        Parent Join = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(Join , 800 , 600);
        Stage primaryStage = (Stage)close.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("primaryStage = " + primaryStage);
        System.out.println("scene = " + scene);


    }catch (Exception e){
        System.out.println(e);
    }

    }
} //  Class End
