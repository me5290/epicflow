package org.example.epicflow.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.epicflow.MainApplication;

import java.net.URL;
import java.util.ResourceBundle;

public class MonsterController implements Initializable {


//공격 버튼
@FXML
    Button Attackbtn;
//후퇴 버튼
@FXML
    Button runBtn;
//인벤 버튼
@FXML
Button invenBtn;


@Override
    public void initialize(URL location, ResourceBundle resources) {
    Attackbtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("<공격> 몬스터 공격 완료");
        }
    });


//    public void runBtn(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("후퇴");
//        alert.setHeaderText("후퇴하시겠습니까?");
//        alert.setContentText("정말로 후퇴하시겠습니까?");
//
//        if(alert.showAndWait().get() == ButtonType.OK){
//            Scene = (Stage) runBtn.getScene().getWindow();
//            System.out.println("후퇴하셨습니다");
//            Scene.close();
//        }
//    }





//@Override
//    public void attackbtn(ActionEvent event){
//    if(event.getSource() == attackbtn){
//        System.out.println("몬스터 공격 완료");
//    }
//}



    }

}
