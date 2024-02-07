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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.epicflow.MainController;
import org.example.epicflow.model.dao.MemberDao;
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
    @FXML private ImageView character,normalEffect,skill1Effect,skill2Effect,skill3Effect,monsterEffect1,monsterEffect2,defenceEffect;
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
    @FXML private Text test123; // 스텟 포인트 표시 텍스트
    @FXML private Button powertext;
    @FXML private Pane win;     // 플레이어 승리 시 화면 출력
    @FXML private Pane lose;    // 플레이어 패배 시 화면 출력
    @FXML private Text strtest,dextest,wistest;

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

    // 몬스터 최대 체력 변수
    int monsterDecrease = monsterDtos.getMonsterHp();

    // 몬스터 현재 체력 변수
    double monsterRenewal;

    double playerPercent;

    // 플레이어 최대 체력 변수
    int playerDecrease;

    // 플레이어 현재 체력 변수
    int playerRenewal;

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

            playerDecrease = player.getMhp();
            playerRenewal = player.getHp();

            // 경험치와 레벨과 포인트 갱신
            levelUp();

            // 플레이어 hp
            playerHpBar.setProgress((double)player.getHp()/player.getMhp());
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

            // 이펙트 효과 보이기
            normalEffect.setVisible(true);

            // 버튼 숨기기
            btnlist.setVisible(false);

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

    // 스킬1 이벤트
    public void skill1(){

        if( turnState && player.getMp() >= 10){
            attacklist.setVisible(false);
            btnlist.setVisible(true);

            double minDamage = player.getSkillpower()-(player.getSkillpower()*0.1);
            double maxDamage = player.getSkillpower()+(player.getSkillpower()*0.1);

            int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

            int lastDamage = damage - monsterDtos.getMonsterDefence();

            monsterHeatBox.setText(Integer.toString(lastDamage));

            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }

            // 마나 감소
            player.setMp(player.getMp()-10);
            playerMp.setText(Integer.toString(player.getMp()));
            playerMpBar.setProgress((double) player.getMp()/player.getMmp());

            turnState = !turnState;

            // 이펙트 효과 보이기
            skill1Effect.setVisible(true);

            // 버튼 숨기기
            btnlist.setVisible(false);

            System.out.println("플레이어 스킬1 성공");

            // 몬스터 체력 갱신 메소드 호출
            monsterHpRenewal();

            // 몬스터 체력 0일때 종료
            monsterRefresh();
        }else{
            // 본인 턴이 아닐때. 버튼 숨기기
            btnlist.setVisible(false);
        }
    }

    // 스킬2 이벤트
    public void skill2(){

        if( turnState && player.getMp() >= 20){
            attacklist.setVisible(false);
            btnlist.setVisible(true);

            double minDamage = player.getSkillpower()-(player.getSkillpower()*0.1);
            double maxDamage = player.getSkillpower()+(player.getSkillpower()*0.1);

            int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

            int lastDamage = (int)(damage*1.2) - monsterDtos.getMonsterDefence();

            monsterHeatBox.setText(Integer.toString(lastDamage));

            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }

            // 마나 감소
            player.setMp(player.getMp()-20);
            playerMp.setText(Integer.toString(player.getMp()));
            playerMpBar.setProgress((double) player.getMp()/player.getMmp());

            turnState = !turnState;

            // 이펙트 효과 보이기
            skill2Effect.setVisible(true);

            // 버튼 숨기기
            btnlist.setVisible(false);

            System.out.println("플레이어 스킬2 성공");

            // 몬스터 체력 갱신 메소드 호출
            monsterHpRenewal();

            // 몬스터 체력 0일때 종료
            monsterRefresh();
        }else{
            // 본인 턴이 아닐때. 버튼 숨기기
            btnlist.setVisible(false);
        }
    }

    // 스킬3 이벤트
    public void skill3(){

        if( turnState && player.getMp() >= 30){
            attacklist.setVisible(false);
            btnlist.setVisible(true);

            double minDamage = player.getSkillpower()-(player.getSkillpower()*0.1);
            double maxDamage = player.getSkillpower()+(player.getSkillpower()*0.1);

            int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

            int lastDamage = (int)(damage*1.5) - monsterDtos.getMonsterDefence();

            monsterHeatBox.setText(Integer.toString(lastDamage));

            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }

            // 마나 감소
            player.setMp(player.getMp()-30);
            playerMp.setText(Integer.toString(player.getMp()));
            playerMpBar.setProgress((double) player.getMp()/player.getMmp());

            turnState = !turnState;

            // 이펙트 효과 보이기
            skill3Effect.setVisible(true);

            // 버튼 숨기기
            btnlist.setVisible(false);

            System.out.println("플레이어 스킬3 성공");

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

        // 버튼 숨기기
        btnlist.setVisible(false);

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
            defenceEffect.setVisible(true);
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
        playerSave();
    }

    // 몬스터 공격 메소드
    public void monsterAttack(){
            if( !turnState ){
                double minDamage = monsterDtos.getMonsterPower()-(monsterDtos.getMonsterPower()*0.1);
                double maxDamage = monsterDtos.getMonsterPower()+(monsterDtos.getMonsterPower()*0.1);

                int damage = random.nextInt((int)minDamage ,(int)maxDamage+1);

                int lastDamage = damage - player.getDefence();

                playerHeatBox.setText(Integer.toString(lastDamage));

                playerRenewal = playerRenewal-lastDamage;

                System.out.println(playerRenewal);

                // 플레이어 현재 체력상태 저장
                player.setHp(playerRenewal);

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
            normalEffect.setVisible(false);
            skill1Effect.setVisible(false);
            skill2Effect.setVisible(false);
            skill3Effect.setVisible(false);
        }  );
        new Thread(sleeper).start();
}

    // 플레이어체력 갱신 메소드
    public void playerHpRenewal(){
        // 백분율 만들기
        playerPercent = (double) playerRenewal / player.getMhp();

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
            playerHpBar.setProgress(playerPercent);
            playerHp.setText(Integer.toString(playerRenewal));
            monsterHeatBox.setVisible(false);
            monsterEffect1.setVisible(true);
        }  );

        new Thread(sleeper).start();
    }

    // 플레이어 체력 0일때 종료 메소드
    public void playerRefresh(){
        if(playerDecrease <= 0){
            System.out.println("패배");
            loseView();
            BattleDao.getInstance().playerNowInfor(player);
            // villageBtn();
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
                defenceEffect.setVisible(false);
                monsterEffect1.setVisible(false);
                btnlist.setVisible(true);
            }  );

            new Thread(sleeper).start();
        }
    }

    // 몬스터 체력 0일때 종료 메소드
    public void monsterRefresh(){
        if(monsterDecrease <= 0){
            player.setExp(player.getExp()+monsterDtos.getDropExp());
            player.setMoney(player.getMoney()+monsterDtos.getDropGold());
            BattleDao.getInstance().playerNowInfor(player);
            System.out.println("승리");
            winView();
            // 플레이어 현재 데이터 저장
            BattleDao.getInstance().playerNowInfor(player);
            System.out.println(player.getExp());
            System.out.println(player.getMoney());
            // villageBtn();
        }else{
            monsterAttack();
        }
    }

    // 레벨업,경험치 출력 메소드(마을,배틀에서 필수)
    public void levelUp(){
        playerexp.setStyle("-fx-accent: yellow;");
        if(player.getExp() >= 51400){
            playerexp.setProgress(1);
            if(player.getLevel() != 11){
                player.setLevel(11);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 25800 && player.getExp() < 51400){
            playerexp.setProgress((double)player.getExp()/51400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("51400");
            if(player.getLevel() != 10){
                player.setLevel(10);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 13000 && player.getExp() < 25800){
            playerexp.setProgress((double)player.getExp()/25800);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("25800");
            if(player.getLevel() != 9){
                player.setLevel(9);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 6600 && player.getExp() < 13000){
            playerexp.setProgress((double)player.getExp()/13000);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("13000");
            if(player.getLevel() != 8){
                player.setLevel(8);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 3400 && player.getExp() < 6600){
            playerexp.setProgress((double)player.getExp()/6600);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("6600");
            if(player.getLevel() != 7){
                player.setLevel(7);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 1800 && player.getExp() < 3400){
            playerexp.setProgress((double)player.getExp()/3400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("3400");
            if(player.getLevel() != 6){
                player.setLevel(6);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 1000 && player.getExp() < 1800){
            playerexp.setProgress((double)player.getExp()/1800);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("1800");
            if(player.getLevel() != 5){
                player.setLevel(5);
                player.setStatpoint(player.getStatpoint()+10);
            }
        }else if(player.getExp() >= 600 && player.getExp() < 1000){
            playerexp.setProgress((double)player.getExp()/1000);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("1000");
            if(player.getLevel() != 4){
                player.setLevel(4);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 400 && player.getExp() < 600){
            playerexp.setProgress((double)player.getExp()/600);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("600");
            if(player.getLevel() != 3){
                player.setLevel(3);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() >= 100 && player.getExp() < 400){
            playerexp.setProgress((double)player.getExp() /400);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("400");
            if(player.getLevel() != 2){
                player.setLevel(2);
                player.setStatpoint(player.getStatpoint()+5);
            }
        }else if(player.getExp() < 100){
            playerexp.setProgress((double)player.getExp()/100);
            playerExp.setText(Integer.toString(player.getExp()));
            playerMaxExp.setText("100");
        }
    }

    // 마을로 이동 메소드
    public void villageBtn(){
        // 도망시 플레이어 정보 저장
        boolean result = BattleDao.getInstance().playerNowInfor(player);
        if (result){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("village.fxml"));
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage)villageBtn.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            }catch (Exception e){
                System.out.println(e);
            } // try e
        } // if e
    } // villageBtn m e

    // 플레이어 승리시 화면표시
    public void winView(){
        System.out.println("함수가 실행됩니다.");
        win.setVisible(true);
    }
    public void loseView(){
        System.out.println("함수가 실행됩니다.");
        lose.setVisible(true);
    }

    //힘 메소드
    public void str(){
        if(player.getStatpoint() != 0){
            player.setStatpoint(player.getStatpoint()-1);
            test123.setText(Integer.toString(player.getStatpoint()));
            player.setStr(player.getStr()+1);
            strtest.setText(Integer.toString(player.getStr()));
            // str에 따른 스텟 변경
            player.setMhp(player.getMhp()+5);
            player.setPower(player.getPower()+2);
            player.setDefence(player.getDefence()+1);
        }
    }
    //민첩 메소드
    public void dex(){
        if(player.getStatpoint() != 0){
            player.setStatpoint(player.getStatpoint()-1);
            test123.setText(Integer.toString(player.getStatpoint()));
            player.setDex(player.getDex()+1);
            dextest.setText(Integer.toString(player.getDex()));
            // dex에 따른 스텟 변경
            player.setEva(player.getEva()+2);
            player.setSpd(player.getSpd()+2);
            player.setPower(player.getPower()+1);
        }
    }
    //지능 메소드
    public void wis() {
        if (player.getStatpoint() != 0) {
            player.setStatpoint(player.getStatpoint()-1);
            test123.setText(Integer.toString(player.getStatpoint()));
            player.setWis(player.getWis() + 1);
            wistest.setText(Integer.toString(player.getWis()));
            // wis에 따른 스텟 변경
            player.setMmp(player.getMmp()+5);
            player.setEva(player.getEva()+1);
            player.setSkillpower(player.getSkillpower()+3);
        }
    }
    // 스탯 찍고 x 버튼 눌렀을때 DTO에 저장 메소드
    public void playerSave(){
        player.setMhp(player.getMhp());
        player.setHp(player.getHp());
        player.setMmp(player.getMmp());
        player.setMp(player.getMp());
        player.setLevel(player.getLevel());
        player.setExp(player.getExp());
        player.setMoney(player.getMoney());
        player.setStatpoint(player.getStatpoint());
        player.setPower(player.getPower());
        player.setDefence(player.getDefence());
        player.setSkillpower(player.getSkillpower());
        player.setStr(player.getStr());
        player.setDex(player.getDex());
        player.setWis(player.getWis());
        player.setEva(player.getEva());
        player.setSpd(player.getSpd());
        player.setMno(player.getMno());
    }
}
