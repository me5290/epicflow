package org.example.epicflow.controller;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import org.w3c.dom.Text;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import static org.example.epicflow.controller.LoginController.memberNum;

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
    @FXML private ImageView character;
    @FXML private Button statbtn;
    @FXML private AnchorPane statPane;
    @FXML private Button exitstatbtn;
    @FXML private AnchorPane statPaneview;
    @FXML private Button stabtnlist;
    @FXML private Label playerName,monsterName,playerHp,playerMaxHp,playerMp,playerMaxMp,monsterNowHp,monsterMaxHp,playerExp,playerMaxExp,playerHeatBox,monsterHeatBox;
    @FXML private Button villageBtn;
    @FXML private Button statdownbtn;
    @FXML private Button statupbtn;
    @FXML private TextField statupdown;

    // 플레이어 정보 배열 변수
    ArrayList<PlayerDto> playerInfor = PlayerDao.getInstance().playerInfor();

    // 로그인 한 회원 번호 가져오기
    int memberNum = LoginController.memberNum;

    // 로그인 한 플레이어 정보 저장 변수
    PlayerDto player = new PlayerDto();

    // 몬스터 정보 객체 생성
    MonsterDto monsterDtos = new MonsterDto();

    // 랜덤 함수 호출
    Random random = new Random();

    // 턴 상태
    boolean turnState = true;

    // 플레이어 최대 체력 변수
    int playerDecrease;

    // 플레이어 현재 체력 변수
    double playerRenewal;

    // 몬스터 최대 체력 변수
    int monsterDecrease = monsterDtos.getMonsterHp();

    // 몬스터 현재 체력 변수
    double monsterRenewal;

    // 플레이어 정보 player 변수에 저장
    public void memberNum(){
        for (int i = 0; i < playerInfor.size(); i++){
            if(playerInfor.get(i).getMno() == memberNum){
                player = playerInfor.get(i);
            }
        }
    }

    // ============================== 메소드 ============================== //

    // 초기값 세팅
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // 플레이어 정보 찾아오기 메소드 실행
            memberNum();

            // 플레이어 최대체력 저장
            playerDecrease = player.getMhp();

            // 경험치와 레벨과 포인트 갱신
            levelUp();

            // 플레이어 hp
            playerHpBar.setProgress(player.getMhp()*0.01);
            playerHpBar.setStyle("-fx-accent: red;");

            // 플레이어 mp
            playerMpBar.setProgress(player.getMp()*2*0.01);
            playerMpBar.setStyle("-fx-accent: blue;");

            // 몬스터 hp
            monsterHp.setProgress(monsterDtos.getMonsterHp()/monsterDtos.getMonsterHp());
            monsterHp.setStyle("-fx-accent: purple;");

            // 초보 모험가(job = 0)캐릭터 출력
//            if(player.getJob() == 0){
//                character.setImage(new Image("@..\\img\\character.png"));
//            }

            // 실행 시 기본 버튼 보이기
            btnlist.setVisible(true);

            // 실행 시 플레이어 닉네임 보이기
            playerName.setText(player.getPname());

            // 실행 시 몬스터 닉네임 보이기
            monsterName.setText(monsterDtos.getMonsterName());

            // 플레이어 체력 출력
            playerHp.setText(Integer.toString(player.getHp()));

            // 플레이어 최대체력 출력
            playerMaxHp.setText(Integer.toString(player.getMhp()));

            // 플레이어 마나 출력
            playerMp.setText(Integer.toString(player.getMp()));

            // 플레이어 최대마나 출력
            playerMaxMp.setText(Integer.toString(player.getMmp()));

            // 몬스터 현재 체력 출력
            monsterNowHp.setText(Integer.toString(monsterDtos.getMonsterHp()));

            // 몬스터 최대체력 출력
            monsterMaxHp.setText(Integer.toString(monsterDtos.getMonsterHp()));
        }catch (Exception e){
            System.out.println(e);
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
    public void nomalAttack(){

        if( turnState ){
            attacklist.setVisible(false);
            btnlist.setVisible(true);

            double minDamage = player.getPower()-(player.getPower()*0.1);
            double maxDamage = player.getPower()+(player.getPower()*0.1);

            int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

            int lastDamage = damage - monsterDtos.getMonsterDefence();

            monsterHeatBox.setText(Integer.toString(lastDamage));

            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }

            turnState = !turnState;

            System.out.println("플레이어 공격 성공");

            // 몬스터 체력 갱신 메소드 호출
            monsterHpRenewal();

            // 몬스터 체력 0일때 종료
            monsterRefresh();
        }else{
            // 본인 턴이 아닐때. 버튼 숨기기
            btnlist.setVisible(false);
        }
    }

    // 방어 버튼 클릭 메소드
    public void defence(){
        System.out.println("방어실행");
        attacklist.setVisible(false);
        btnlist.setVisible(true);

        double minDamage = monsterDtos.getMonsterPower()-(monsterDtos.getMonsterPower()*0.1);
        double maxDamage = monsterDtos.getMonsterPower()+(monsterDtos.getMonsterPower()*0.1);

        int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

        int lastDamage = (damage - player.getDefence());

        double defenceDamage = lastDamage*0.2;

        playerHeatBox.setText(Integer.toString((int)defenceDamage));

        if(player.getMhp() == playerDecrease){
            playerDecrease = player.getMhp()-(int)defenceDamage;
        }else{
            playerDecrease = (int)(playerRenewal*player.getMhp())-(int)defenceDamage;
        }

        turnState = !turnState;

        System.out.println("몬스터 공격 성공");

        // 시간차
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(3000); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded( event -> {
            playerHeatBox.setVisible(true);
            playerHp.setText(Integer.toString(playerDecrease));
        }  );
        new Thread(sleeper).start();

        // 플레이어 체력 갱신 메소드 호출
        playerHpRenewal();

        // 플레이어 체력 0일때 종료
        playerRefresh();
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

    //캐릭터 스탯창 버튼
    public void stabtnlist(){
        statPaneview.setVisible(true);
    }
    public void exitstatbtn(){
        statPaneview.setVisible(false);
    }



    // 몬스터 공격 메소드
    public void monsterAttack(){
            if( !turnState ){
                double minDamage = monsterDtos.getMonsterPower()-(monsterDtos.getMonsterPower()*0.1);
                double maxDamage = monsterDtos.getMonsterPower()+(monsterDtos.getMonsterPower()*0.1);

                int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

                int lastDamage = damage - player.getDefence();

                playerHeatBox.setText(Integer.toString(lastDamage));

                if(player.getMhp() == playerDecrease){
                    playerDecrease = player.getMhp()-lastDamage;
                }else{
                    playerDecrease = (int)(playerRenewal*player.getMhp())-lastDamage;
                }

                turnState = !turnState;

                System.out.println("몬스터 공격 성공");

                // 플레이어 체력 갱신 메소드 호출
                playerHpRenewal();

                // 플레이어 체력 0일때 종료
                playerRefresh();

            }else{
                btnlist.setVisible(true);
            }
    }

    // 몬스터체력 갱신 메소드
    public void monsterHpRenewal(){
        // 백분율 만들기
        monsterRenewal = (double) monsterDecrease / (double)monsterDtos.getMonsterHp();

        // 시간차
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(1500); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded( event -> {
            monsterHeatBox.setVisible(true);
            monsterHp.setProgress(monsterRenewal);
            monsterNowHp.setText(Integer.toString(monsterDecrease));
        }  );
        new Thread(sleeper).start();
}

    // 플레이어체력 갱신 메소드
    public void playerHpRenewal(){
        // 백분율 만들기
        playerRenewal = (double) playerDecrease / (double)player.getMhp();

        // 시간차
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(3000); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded( event -> {
            playerHeatBox.setVisible(true);
            playerHpBar.setProgress(playerRenewal);
            playerHp.setText(Integer.toString(playerDecrease));
            monsterHeatBox.setVisible(false);
        }  );

        new Thread(sleeper).start();
    }

    // 플레이어 체력 0일때 종료 메소드
    public void playerRefresh(){
        if(playerDecrease <= 0){
            System.out.println("패배");
        }else{
            // 시간차
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try { Thread.sleep(4500); }
                    catch (InterruptedException e) { }
                    return null;
                }
            };
            sleeper.setOnSucceeded( event -> {
                playerHeatBox.setVisible(false);
            }  );

            new Thread(sleeper).start();
        }
    }

    // 몬스터 체력 0일때 종료 메소드
    public void monsterRefresh(){
        if(monsterDecrease <= 0){
            player.setExp(monsterDtos.getDropExp());
            player.setMoney(monsterDtos.getDropGold());
            System.out.println("승리");
        }else{
            monsterAttack();
        }
    }

    //캐릭터 스텟창 올리기 내리기 메소드ㅜ
    public void statupdown(ActionEvent actionEvent){
        int count = 0;
        if(actionEvent.getSource()== statupbtn){
            count++;

        }else if ( actionEvent.getSource()== statdownbtn){
            count--;
        }
   }

    // 레벨업,경험치 출력 메소드(마을,배틀에서 필수)
    public void levelUp(){
        if(player.getExp() >= 51400){
            playerexp.setProgress(1);
            if(player.getLevel() != 11){
                player.setLevel(11);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 25800 && player.getExp() < 51400){
            playerexp.setProgress(player.getExp()/51400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("51400");
            if(player.getLevel() != 10){
                player.setLevel(10);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 13000 && player.getExp() < 25800){
            playerexp.setProgress(player.getExp()/25800);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("25800");
            if(player.getLevel() != 9){
                player.setLevel(9);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 6600 && player.getExp() < 13000){
            playerexp.setProgress(player.getExp()/13000);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("13000");
            if(player.getLevel() != 8){
                player.setLevel(8);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 3400 && player.getExp() < 6600){
            playerexp.setProgress(player.getExp()/6600);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("6600");
            if(player.getLevel() != 7){
                player.setLevel(7);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 1800 && player.getExp() < 3400){
            playerexp.setProgress(player.getExp()/3400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("3400");
            if(player.getLevel() != 6){
                player.setLevel(6);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 1000 && player.getExp() < 1800){
            playerexp.setProgress(player.getExp()/1800);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("1800");
            if(player.getLevel() != 5){
                player.setLevel(5);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 600 && player.getExp() < 1000){
            playerexp.setProgress(player.getExp()/1000);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("1000");
            if(player.getLevel() != 4){
                player.setLevel(4);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 400 && player.getExp() < 600){
            playerexp.setProgress(player.getExp()/600);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("600");
            if(player.getLevel() != 3){
                player.setLevel(3);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 100 && player.getExp() < 400){
            playerexp.setProgress(player.getExp()/400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("400");
            if(player.getLevel() != 2){
                player.setLevel(2);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else{
            playerexp.setProgress(player.getExp()/100);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("100");
        }
        playerexp.setStyle("-fx-accent: yellow;");
    }

    // 마을로 이동 메소드
    public void villageBtn(){
        try {
        Parent root = FXMLLoader.load(getClass().getResource("village_test.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)villageBtn.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
