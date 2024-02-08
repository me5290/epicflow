package org.example.epicflow.controller;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.epicflow.MainApplication;
import org.example.epicflow.model.dao.MemberDao;
import org.example.epicflow.model.dao.PlayerDao;
import org.example.epicflow.model.dto.MemberDto;
import org.example.epicflow.model.dto.PlayerDto;
import javax.xml.stream.Location;
import java.io.IOException;


public class LoginController extends MainApplication {

    public static int memberNum;            // 회원번호 저장
    public Pane mainVuew;                   // 첫 시작 뷰
    public TextField inputId = null;        // 로그인시 아이디 입력칸
    public PasswordField inputPw = null;    // 로그인시 비밀번호 입력칸
    public ImageView LoginIcon;             // 로그인 화면 메인 이미지
    public Label loginLabel = null;         // 로그인 성공/실패시 텍스트 표시
    public Button loginSbtn;                // 로그인 성공/실패 버튼
    public Button membership;               // 회원가입 클릭 버튼
    public Pane memberView;                 // 안내 창 화면
    public Pane cNamePane;                  // 로그인 성공시 캐릭터 생성 화면
    public TextField cName;                 // 캐릭터 이름 입력창
    public Button generation;               // 캐릭터 생성 버튼
    public Button cancel;                   // 캐릭터 생성 취소버튼

    // ==== 회원번호 [ return = 회원번호 ]
    public static int getMemberNum() {
        return memberNum;
    }

// ================================ 로그인 s ================================ //
    // ===== 로그인 메소드
    public void Onlogin(){
        while (true){
            int memberNum;

            // 사용자가 아이디 또는 비밀번호를 입력하지 않았을 때
                // === .getText().isEmpty() 텍스트가 빈값인지 확인
            if (inputId.getText().isEmpty() || inputPw.getText().isEmpty()){
                memberView.setVisible(true);    // 사용자에게 보여줄 안내 화면
                loginLabel.setText("아이디 또는 비밀번호를 입력해 주세요.");
                break;
            }
            MemberDto memberDto = new MemberDto();
            memberDto.setMid(inputId.getText());
            memberDto.setMpw(inputPw.getText());
            memberNum = MemberDao.getInstance().Onlogin(memberDto);
            this.memberNum = memberNum;

            // 사용자가 아이디와 비밀번호의 정보가 없을 때
            if (getMemberNum() == 0 ){
                memberView.setVisible(true);
                loginLabel.setText("존재하지 않는 회원 입니다.");
                inputId.setText("");
                inputPw.setText("");
                break;
            }else {
                // 캐릭터 존재 여부 확인 메소드
                if (characterFind()){
                    // 캐릭터 있으면 마을 씬 이동
                    villageScene();
                }else {
                    // 캐릭터 없으면 캐릭터 생성 뷰 생성
                    inputId.setText("");
                    inputPw.setText("");
                    cNamePane.setVisible(true);
                }
                break;
            }
        } // w e
    } // m e
// ================================ 로그인 e ================================ //

    // ==== 마을 화면 전환 메소드
    public void villageScene(){
        try {
            Parent battle = FXMLLoader.load(getClass().getResource("village.fxml"));
            Scene scene = new Scene(battle, 1300, 498 );
            Stage primaryStage = (Stage) membership.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // 화면 조정 막기
            // 마을 위치 시작 지점
            primaryStage.setX(400);
            primaryStage.setY(250);
            primaryStage.show();
        }catch (Exception e){
            System.out.println("[ ※ 안내 : villageScene 오류 입니다. ※ ]" + e);
        }

    }
    
    // ==== 회원가입 화면전환 메소드
    public void joinMembership(){
        try {
            Parent Join = FXMLLoader.load(getClass().getResource("join.fxml"));
            Scene scene = new Scene(Join , 600 , 400);
            Stage primaryStage = (Stage)membership.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println("[ ※ 안내 : joinMembership 오류 입니다. ※ ]" + e);
        }

    }

    // ==== 종료 메소드
    public void OnExit(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("종료합니다.");
        alert.show();
        Platform.exit();
    }

    // ==== 안내 창 화면 숨기기
    public void viewHide(){
        memberView.setVisible(false);

    }

    // ==== 캐릭터 유무 확인 메서드 [ return = true 캐릭터 있음, return = false 캐릭터 없음 ]
    public boolean characterFind(){
        boolean ch = PlayerDao.getInstance().characterFind(memberNum);
        if(ch){
            return true;
        }
        return false;
    }


    // ==== 캐릭터 생성 버튼 클릭 시 이름 DB에 업데이트
    public void characterGeneration(){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPname(cName.getText());
        playerDto.setMno(memberNum);
        boolean result = PlayerDao.getInstance().characterGeneration(playerDto);
        if (result){
            villageScene();
        }else {
            System.out.println("[ ※ 안내 : UPDATE 실패 입니다. ※ ]");
        }
    }

    // ==== 캐릭터 생성 화면 숨기기
    public void characterCancel(){
        cNamePane.setVisible(false);
    }
}
