package org.example.epicflow.controller;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.epicflow.MainController;
import org.example.epicflow.model.dao.PlayerDao;
import org.example.epicflow.model.dto.PlayerDto;
import org.example.epicflow.model.dao.BattleDao;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    @FXML private ProgressBar playerHpBar;
    @FXML private ProgressBar playerMpBar;
    @FXML private ProgressBar playerexp;
    @FXML private AnchorPane attacklist;
    @FXML private HBox btnlist;

    private int persent = 0;    // 프로그레스 바 게이지 초기값

    ArrayList<PlayerDto> playerInfor = PlayerDao.getInstance().playerInfor();

    //File chobo = new File();

    // 공격 버튼 클릭 이벤트
    public void attackBtnList(){
        attacklist.setVisible(true);
        btnlist.setVisible(false);
    }

    // 뒤로가기 버튼 클릭 이벤트
    public void backBtn(){
        attacklist.setVisible(false);
        btnlist.setVisible(true);
    }

    // 기본공격 버튼 클릭 이벤트
    public void nomalAttack(){
        System.out.println(playerInfor.get(1).getPower());
        for(int i = 0; i < playerInfor.size(); i++){
            System.out.println(playerInfor.get(i).toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 플레이어 경험치
        playerexp.setProgress(playerInfor.get(1).getExp()*0.01);

        // 플레이어 hp
        playerHpBar.setProgress(80*0.01);

        // 플레이어 mp
        playerMpBar.setProgress(50*0.01);

        // 캐릭터 출력
        if(playerInfor.get(1).getJob() == 0){

        }
    }
}
