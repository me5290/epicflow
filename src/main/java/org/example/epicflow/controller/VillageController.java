package org.example.epicflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VillageController {
    @FXML private Pane opcitybackground;
    @FXML private HBox huntingchoice;
    @FXML private Button backBtn;
    @FXML private Pane field;
    // === 로그인 성공하면 빌리지
    // === 상점 클릭하면 상점 fxml
    public void store(){
        System.out.println("여기는 상점입니다.");
    }

    // === 사냥 클릭하면 사냥 fxmml

    public void field(){
        try {
        Parent battle = FXMLLoader.load(getClass().getResource("battle.fxml"));
        Scene scene = new Scene(battle , 800 , 600);
        Stage primaryStage = (Stage)field.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("primaryStage = " + primaryStage);
        System.out.println("scene = " + scene);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void huntingGround(){
        opcitybackground.setVisible(true);
        huntingchoice.setVisible(true);
        backBtn.setVisible(true);
    }

    public void back(){
        opcitybackground.setVisible(false);
        huntingchoice.setVisible(false);
        backBtn.setVisible(false);
    }
}
