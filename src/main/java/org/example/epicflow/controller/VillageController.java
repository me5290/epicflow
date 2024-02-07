package org.example.epicflow.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.epicflow.model.dao.PlayerDao;
import org.example.epicflow.model.dto.PlayerDto;

public class VillageController {
    @FXML private Pane opcitybackground;
    @FXML private HBox huntingchoice;
    @FXML private Button backBtn;
    @FXML private Pane field;
    @FXML public Pane mainPane; // 메인 화면
    @FXML public Pane bg;
    @FXML public Button store;  // 상점 버튼
    @FXML public Button motelBtn; // 여관 버튼

    @FXML public Pane motelPane; // 여관 버튼 클릭시 변경할 화면
    @FXML public ImageView motelBg; // 여관 백그라운드 이미지
    @FXML public Label motelLabel1; // 여관 주인 텍스트1
    @FXML public Label motelLabel2; // 여관 주인 텍스트1
    @FXML public Button useMtBtn;   // 여관을 사용 한다 버튼
    @FXML public Button outMtBtn;   // 여관에서 나간다 버튼

    // 로그인한 회원 데이터 로그인 컨트롤러에서 가져오기
    int memberNum = LoginController.getMemberNum();

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
        Stage primaryStage = (Stage)field.getScene().getWindow(); // 여기 오류
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

    // 여관 버튼 클릭 이벤트 실행
    public void motelBtnOn(){
        System.out.println("여관 버튼 실행 ");
        bg.setVisible(false);
        motelPane.setVisible(true);
    } // motelBtnOn m e

    // 여관 에서 숙박 버튼 클릭시 이벤트
    public void useMtBtnOn(){
        System.out.println("숙박 버튼 클릭 CONTROLLER");
        System.out.println("숙박버튼 이용 사용자 넘버" + memberNum);
        PlayerDto playerDto = new PlayerDto();
        playerDto =  PlayerDao.getInstance().playerMoneySc(memberNum);
        int pMoney = playerDto.getMoney();
        int Mhp = playerDto.getMhp();
        int Mmp = playerDto.getMmp();

        System.out.println("pMoney = " + pMoney);
        if (pMoney == -999){
            System.out.println("치명적인 오류 발생");
        }
        if (pMoney < 10){
            System.out.println("돈 부족");
        }else{
            System.out.println("돈 있음");
            pMoney -= 10;
            PlayerDao.getInstance().useMtBtnOn(memberNum , pMoney , Mhp , Mmp);
            // 박시현 여기까지 작업 완료
            // 후에 할일은 회복후 마을로 이동할 것인지 어떻게할것인지 선택
        }




    }

    // 여관 에서 나가기 버튼 클릭시 이벤트
    public void outMtBtnOn(){
        System.out.println("여관 나가기 버튼 실행 CONTROLLER");
        motelPane.setVisible(false);
        bg.setVisible(true);
    }

//    // 테스트 없애도됨
//    public void huntingGround(){
//        huntingchoice.setVisible(true);
//        backBtn.setVisible(true);
//
//    }
//    public void back(){
//        huntingchoice.setVisible(false);
//        backBtn.setVisible(false);
//    }

} // c e
