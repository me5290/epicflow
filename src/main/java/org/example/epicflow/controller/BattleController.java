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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.epicflow.MainController;
import org.example.epicflow.model.dao.PlayerDao;
import org.example.epicflow.model.dto.MonsterDto;
import org.example.epicflow.model.dto.PlayerDto;
import org.example.epicflow.model.dao.BattleDao;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class BattleController implements Initializable {

    @FXML private ProgressBar playerHpBar;
    @FXML private ProgressBar playerMpBar;
    @FXML private ProgressBar playerexp;
    @FXML private ProgressBar monsterHp;
    @FXML private AnchorPane attacklist;
    @FXML private HBox btnlist;
    @FXML private Pane exitalert;

    // 플레이어 정보 배열 변수
    ArrayList<PlayerDto> playerInfor = PlayerDao.getInstance().playerInfor();

    // 몬스터 정보 객체 생성
    MonsterDto monsterDtos = new MonsterDto();

    // 랜덤 함수 호출
    Random random = new Random();

    // 배틀 진행 상황 변수
    boolean start = true;

    // ============================== 메소드 ============================== //

    // 초기값 세팅
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 플레이어 경험치
        playerexp.setProgress(50*0.01);
        playerexp.setStyle("-fx-accent: yellow;");

        // 플레이어 hp
        playerHpBar.setProgress(playerInfor.get(0).getHp()*0.01);
        playerHpBar.setStyle("-fx-accent: red;");

        // 플레이어 mp
        playerMpBar.setProgress(playerInfor.get(0).getMp()*2*0.01);
        playerMpBar.setStyle("-fx-accent: blue;");

        // 캐릭터 출력
        if(playerInfor.get(1).getJob() == 0){

        }

        // 몬스터 hp
        monsterHp.setProgress(playerInfor.get(0).getHp()*0.01);
        monsterHp.setStyle("-fx-accent: purple;");

        battleStart();
    }

    // 배틀 시작 메소드

    public void battleStart(){
        while (start){
            nomalAttack();

            int monsterNowHp = nomalAttack();
            int monsterRenewal = 100;

            // 몬스터 hp
            monsterHp.setProgress(monsterRenewal*0.01);
            monsterHp.setStyle("-fx-accent: purple;");

            break;
        }
    }

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
    public int nomalAttack(){
        double minDamage = playerInfor.get(0).getPower()-(playerInfor.get(0).getPower()*0.1);
        double maxDamage = playerInfor.get(0).getPower()+(playerInfor.get(0).getPower()*0.1);

        int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

        int lastDamage = damage - monsterDtos.getMonsterDefence();

        int monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
        System.out.println(damage);
        System.out.println(lastDamage);
        System.out.println(monsterDecrease);

        return monsterDecrease;
    }

    // 도망 버튼 클릭 이벤트
    public void exit(){
        exitalert.setVisible(true);
    }
    public void exitBack(){
        exitalert.setVisible(false);
    }

}
