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
        // 플레이어 정보 찾아오기 메소드 실행
        memberNum();

        // 플레이어 최대체력 저장
        playerDecrease = player.getMhp();

        // 플레이어 경험치
        playerexp.setProgress(player.getExp()*0.01);
        playerexp.setStyle("-fx-accent: yellow;");

        // 플레이어 hp
        playerHpBar.setProgress(player.getMhp()*0.01);
        playerHpBar.setStyle("-fx-accent: red;");

        // 플레이어 mp
        playerMpBar.setProgress(player.getMp()*2*0.01);
        playerMpBar.setStyle("-fx-accent: blue;");
        System.out.println(monsterDtos.getMonsterHp());

        // 몬스터 hp
        monsterHp.setProgress(monsterDtos.getMonsterHp()/monsterDtos.getMonsterHp());
        monsterHp.setStyle("-fx-accent: purple;");

        // 초보 모험가(job = 0)캐릭터 출력
        if(player.getJob() == 0){
            character.setImage(new Image("C:\\Users\\504\\Desktop\\epicflow\\src\\main\\resources\\img\\character.png"));
        }

        // 실행 시 기본 버튼 보이기
        btnlist.setVisible(true);

        System.out.println(player);
        System.out.println(memberNum);
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
            System.out.println("플레이어 데미지"+damage);
            int lastDamage = damage - monsterDtos.getMonsterDefence();
            System.out.println("최종 데미지"+lastDamage);
            if(monsterDtos.getMonsterHp() == monsterDecrease){
                monsterDecrease = monsterDtos.getMonsterHp()-lastDamage;
            }else{
                monsterDecrease = (int)(monsterRenewal*monsterDtos.getMonsterHp())-lastDamage;
            }
            System.out.println("몬스터 처맞은 후 체력"+monsterDecrease);
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


        if(player.getMhp() == playerDecrease){
            playerDecrease = player.getMhp()-(int)defenceDamage;
        }else{
            playerDecrease = (int)(playerRenewal*player.getMhp())-(int)defenceDamage;
        }

        turnState = !turnState;

        System.out.println("몬스터 공격 성공");

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
                System.out.println("몬스터 최종 데미지"+lastDamage);
                System.out.println(playerDecrease);
                System.out.println(playerRenewal+"*"+player.getMhp()+"-"+lastDamage);
                if(player.getMhp() == playerDecrease){
                    playerDecrease = player.getMhp()-lastDamage;
                }else{
                    playerDecrease = (int)(playerRenewal*player.getMhp())-lastDamage;
                }
                System.out.println("플레이어 처 맞은후 체력"+playerDecrease);
                turnState = !turnState;

                System.out.println("몬스터 공격 성공");

                // 몬스터 체력 갱신 메소드 호출
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
        monsterHp.setProgress(monsterRenewal);
    }

    // 플레이어체력 갱신 메소드
    public void playerHpRenewal(){
        System.out.println("플레이어 현재체력"+playerDecrease);
        System.out.println("플레이어 최대체력"+player.getMhp());
        // 백분율 만들기
        playerRenewal = (double) playerDecrease / (double)player.getMhp();
        System.out.println("백분율 계산"+playerRenewal);
        playerHpBar.setProgress(playerRenewal);
    }

    // 플레이어 체력 0일때 종료 메소드
    public void playerRefresh(){
        if(playerDecrease <= 0){
            System.out.println("패배");
        }
    }

    // 몬스터 체력 0일때 종료 메소드
    public void monsterRefresh(){
        if(monsterDecrease <= 0){
            player.setExp(monsterDtos.getDropExp());
            player.setMoney(monsterDtos.getDropGold());
            System.out.println("승리");
            System.out.println(player.getExp());
            System.out.println(player.getMoney());
        }else{
            monsterAttack();
        }
    }

    //캐릭터 스텟창 올리기 내리기 메소드ㅜ
//    public void statupdown(ActionEvent actionEvent){
//
//        intCount++;
//    }



}
