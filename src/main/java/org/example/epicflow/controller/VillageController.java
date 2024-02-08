package org.example.epicflow.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.epicflow.model.dao.PlayerDao;
import org.example.epicflow.model.dto.PlayerDto;


import java.net.URL;
import java.util.ResourceBundle;

public class VillageController {
// ================================ 필드 ================================ //
    @FXML private Pane opcitybackground;    // 사냥터 정보 배경
    @FXML private HBox huntingchoice;       // 사냥터 정보 화면
    @FXML private Button backBtn;           // 사냥터 이동
    @FXML private ImageView field;          // 사냥터 이동

    @FXML private Pane slime;               // 몬스터 slime 화면
    @FXML private Pane goblin;              // 몬스터 goblin 화면
    @FXML private Pane mino;                // 몬스터 mino 화면
    @FXML private Pane dragon;              // 몬스터 dragon 화면

    @FXML public Pane mainPane;             // 메인 화면
    @FXML public Pane bg;                   // 마을 화면
    @FXML public Button store;              // 상점 버튼
    @FXML public Button motelBtn;           // 여관 버튼
    @FXML public ImageView motelImg;        // 여관 포탈 이미지

    @FXML public Pane motelPane;            // 여관 버튼 클릭시 변경할 화면
    @FXML public ImageView motelBg;         // 여관 배경 이미지
    @FXML public Label motelLabel1;         // 여관 주인 텍스트1
    @FXML public Label motelLabel2;         // 여관 주인 텍스트2
    @FXML public Button useMtBtn;           // 여관을 사용 버튼
    @FXML public Button outMtBtn;           // 여관 나감 버튼

    int memberNum = LoginController.getMemberNum();     // 로그인 된 회원 번호

    public static int monsterNum;                       // 몬스터번호 저장

// ================================ 메소드 ================================ //

    // === 상점 미구현
    public void store(){
        System.out.println("여기는 상점입니다.");
    }

    // === 몬스터 사냥터에 맞는 몬스터 출력 및 배틀 화면 전환
    @FXML
    public void field( MouseEvent event ) {

        // ==== 몬스터 출력
        Pane pane = (Pane)event.getSource();
        if( pane.getId().equals("slime") ){
            monsterNum = 1;
        }else if(pane.getId().equals("goblin")) {
            monsterNum = 2;
        }else if(pane.getId().equals("mino")) {
            monsterNum = 3;
        }else if(pane.getId().equals("dragon")) {
            monsterNum = 4;
        }

        // ==== 배틀 화면 전환
        try {
            Parent battle = FXMLLoader.load(getClass().getResource("battle.fxml"));
            Scene scene = new Scene(battle , 800 , 600);
            Stage primaryStage = (Stage)field.getScene().getWindow(); // 여기 오류
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println("[ ※ 안내 : field  오류 입니다. ※ ]" +e);
        }

    }

    // ==== 사냥터 클릭시 보이기 메소드
    public void huntingGround(){
        opcitybackground.setVisible(true);
        huntingchoice.setVisible(true);
        backBtn.setVisible(true);
    }

    // ==== 사냥터 클릭시 숨기기 메소드
    public void back(){
        opcitybackground.setVisible(false);
        huntingchoice.setVisible(false);
        backBtn.setVisible(false);
    }

    // ==== 여관 클릭 시 화면 보이기 메소드
    public void motelBtnOn(){
        bg.setVisible(false);
        motelPane.setVisible(true);
    }

    // ==== 여관에서 버튼 작동 시 메소드
    public void useMtBtnOn(){
        PlayerDto playerDto;
        playerDto =  PlayerDao.getInstance().playerMoneySc(memberNum);
        int pMoney = playerDto.getMoney();
        int Mhp = playerDto.getMhp();
        int Mmp = playerDto.getMmp();

        if (pMoney == -999){
            System.out.println("치명적인 오류 발생");
        }
        if (pMoney < 10){
            System.out.println("[ ● 안내 : 돈 부족 ● ]");
        }else{
            System.out.println("[ ● 안내 : 돈 있음 ● ]");
            pMoney -= 10;
            PlayerDao.getInstance().useMtBtnOn(memberNum , pMoney , Mhp , Mmp);
        }
    }

    // ==== 여관 나가기 버튼 클릭시 화면 숨기기 메소드
    public void outMtBtnOn(){
        motelPane.setVisible(false);
        bg.setVisible(true);
    }

} // c e
