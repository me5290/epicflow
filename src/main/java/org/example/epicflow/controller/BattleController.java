package org.example.epicflow.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class BattleController implements Initializable {

    @FXML private ProgressBar playerHpBar;
    @FXML private ProgressBar playerMpBar;
    @FXML private ProgressBar playerexp;
    @FXML private ProgressBar monsterHp;
    @FXML private AnchorPane attacklist;
    @FXML private HBox btnlist;
    @FXML private Pane exitalert;
    @FXML private Button invenBtn;
    @FXML private AnchorPane inventory;

    // 플레이어 정보 배열 변수
    ArrayList<PlayerDto> playerInfor = PlayerDao.getInstance().playerInfor();

    // 몬스터 정보 객체 생성
    MonsterDto monsterDtos = new MonsterDto();

    // 랜덤 함수 호출
    Random random = new Random();

    // 턴 상태
    boolean turnState = true;

    // 몬스터 최대 체력 변수
    int monsterDecrease = monsterDtos.getMonsterHp();

    // 몬스터 현재 체력 변수
    double monsterRenewal;

    // 플레이어 최대 체력 변수
    int playerDecrease = playerInfor.get(0).getMhp();

    // 플레이어 현재 체력 변수
    double playerRenewal;

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

        btnlist.setVisible(true);

    }

    // 배틀 시작 메소드
    public void battleStart(){}

    // 공격 버튼 클릭 이벤트
    public void attackBtnList(){
        attacklist.setVisible(true);
        btnlist.setVisible(false);
    }

    // 뒤로가기 버튼 클릭 이벤트
    public void backBtn(){

    }

    // 기본공격 버튼 클릭 이벤트
    public void nomalAttack(){

        if( turnState ){
            attacklist.setVisible(false);
            btnlist.setVisible(true);

            double minDamage = playerInfor.get(0).getPower()-(playerInfor.get(0).getPower()*0.1);
            double maxDamage = playerInfor.get(0).getPower()+(playerInfor.get(0).getPower()*0.1);

            int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

            int lastDamage = damage - monsterDtos.getMonsterDefence();

            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }

            turnState = !turnState;

            System.out.println("플레이어 공격 성공");

            // 몬스터 체력 갱신 메소드 호출
            monsterHpRenewal();

            if(monsterDecrease <= 0){
                System.out.println("승리");
            }else{
                monsterAttack();
            }
        }else{
            // 본인 턴이 아닐때. 버튼 숨기기
            btnlist.setVisible(false);
        }
    }

    // 도망 버튼 클릭 이벤트
    public void exit(){
        exitalert.setVisible(true);
    }
    public void exitBack(){
        exitalert.setVisible(false);
    }

    //인벤 버튼 클릭 이벤트
    public void invenbtnList() {
        inventory.setVisible(true);

    }
    //인벤 버튼 종료 버튼 이벤트
    public void exitinvenbtn() {

        inventory.setVisible(false);
    }

    // 몬스터 공격 메소드
    public void monsterAttack(){
        System.out.println( "몬스터");
            if( !turnState ){
                double minDamage = monsterDtos.getMonsterPower()-(monsterDtos.getMonsterPower()*0.1);
                double maxDamage = monsterDtos.getMonsterPower()+(monsterDtos.getMonsterPower()*0.1);

                int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

                int lastDamage = damage - playerInfor.get(0).getDefence();

                if(playerInfor.get(0).getMhp() == playerDecrease){
                    playerDecrease = playerInfor.get(0).getMhp()-lastDamage;
                }else{
                    playerDecrease = (int)(playerRenewal*playerInfor.get(0).getMhp())-lastDamage;
                }

                turnState = !turnState;

                System.out.println("몬스터 공격 성공");

                // 몬스터 체력 갱신 메소드 호출
                playerHpRenewal();

                if(playerDecrease <= 0){
                    System.out.println("패배");
                }
            }else{
                btnlist.setVisible(true);
            }
    }

    // 몬스터체력 갱신 메소드
    public void monsterHpRenewal(){
        System.out.println(monsterDecrease);
        System.out.println(monsterDtos.getMonsterHp());
        // 백분율 만들기
        monsterRenewal = (double) monsterDecrease / (double)monsterDtos.getMonsterHp();
        System.out.println(monsterRenewal);
        monsterHp.setProgress(monsterRenewal);
    }

    // 플레이어체력 갱신 메소드
    public void playerHpRenewal(){
        System.out.println(playerDecrease);
        System.out.println(playerInfor.get(0).getMhp());
        // 백분율 만들기
        playerRenewal = (double) playerDecrease / (double)playerInfor.get(0).getMhp();
        System.out.println(playerRenewal);
        playerHpBar.setProgress(playerRenewal);
    }

    // 체력 0일때 종료 메소드
    public void battleEnd(){
        if(monsterDecrease <= 0){
            System.out.println("승리");
        }else if(playerDecrease <= 0){
            System.out.println("패배");
        }
    }

}
