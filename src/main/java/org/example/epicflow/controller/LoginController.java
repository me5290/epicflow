package org.example.epicflow.controller;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.epicflow.MainApplication;
import org.example.epicflow.model.dao.MemberDao;
import org.example.epicflow.model.dto.MemberDto;



public class LoginController extends MainApplication {

    public static int memberNum = 1; // 회원번호 저장
    public Pane mainVuew;       // 첫 시작 뷰
    public TextField inputId = null;    // 로그인시 아이디 입력칸
    public TextField inputPw = null;    // 로그인시 비밀번호 입력칸
    public ImageView LoginIcon;         // 로그인 뷰 이미지
    public Label loginLabel = null;     // 로그인 성공/실패시 텍스트 표시
    public Button loginSbtn;            // 로그인 성공/실패시 뷰
    public Button membership;           // 회원가입 클릭 버ㅓ튼
    public Pane memberView;             // 로그인 시 환영 메세지
    public Pane cNamePane;              // 로그인 성공시 캐릭터 생성 뷰
    public TextField cName;             // 로그인 성공시 캐릭터 이름 받기
    public Button generation;           // 캐릭터 생성 버튼
    public Button cancel;               // 캐릭터 생성 뷰 에서 취소버튼
    public boolean bolean = false;      // 불리언 선언 조건부

    // 회원번호 get
    public static int getMemberNum() {
        return memberNum;
    }

    public void Onlogin(){
        while (true){
            int memberNum;
            // 1. 입력값 처리
            System.out.println(inputId);
            System.out.println(inputPw);
            System.out.println("id = " + inputId.getText());
            System.out.println("pw = " + inputPw.getText());

            // id pw 입력이 빈값일 시 오류 발생
            // 변수명.getText().isEmpty() 텍스트가 빈값인지 확인
            if (inputId.getText().isEmpty() || inputPw.getText().isEmpty()){
                System.out.println("아이디 비번 입력X");
                // Pane 뛰워서 현재 오류 상태 표시
                memberView.setVisible(true);
                loginLabel.setText("아이디 또는 비밀번호를 입력해 주세요.");
                break;
            } // 공백 체크 끝

            // 2. 객체 생성
            MemberDto memberDto = new MemberDto();
            memberDto.setMid(inputId.getText());
            memberDto.setMpw(inputPw.getText());
            System.out.println("memberDto = " + memberDto);
            System.out.println(memberDto.getMid());
            System.out.println(memberDto.getMpw());

            // 3. Dao 객체 넘기기
            memberNum = MemberDao.getInstance().Onlogin(memberDto);
            this.memberNum = memberNum;
            // 4. 처리
            if (getMemberNum() == 0 ){
                // Pane 뛰워서 존재하지않는 회원 표시
                memberView.setVisible(true);
                loginLabel.setText("존재하지 않는 회원 입니다.");
                inputId.setText("");
                inputPw.setText("");
                break;
            }else {
                // Pane 뛰워서 현재 로그인한 회원 정보 표시
                memberView.setVisible(true);
                loginLabel.setText(memberDto.getMid() + "회원님 환영 합니다.");
                System.out.println(memberNum);
                bolean = true;
                break;
            }

        } // w e

        // 배틀 씬 이동
        if (bolean){
            battleScen();
        }

    }
    
    // 회원가입 클릭시 회원가입 페이지로 이동
    public void joinMembership(){
        try {
            Parent Join = FXMLLoader.load(getClass().getResource("EpicflowJoin.fxml"));

            Scene scene = new Scene(Join , 600 , 400);
            Stage primaryStage = (Stage)membership.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("primaryStage = " + primaryStage);
            System.out.println("scene = " + scene);


        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void OnExit(){
        System.out.println("종료합니다.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("종료합니다.");
        alert.show();
        // 종료
        Platform.exit();
    }

    // 로그인 성공 후 로그인한 회원표시 가리기
    public void viewHide(){
        System.out.println("안보이기");
        memberView.setVisible(false);

    }

    // 캐릭터 생성 버튼 클릭 시
    public void characterGeneration(){



    }

    // 캐릭터 생성 뷰 에서 취소 클릭 시
    public void characterCancel(){
        System.out.println("캐릭터생성 뷰 안보이기");
        cNamePane.setVisible(false);

    }

    // 로그인 및 캐릭터 생성 후 전투 씬 이동
    public void battleScen(){
        // 로그인 성공! 배틀신으로 이동
        try {
            Parent battle = FXMLLoader.load(getClass().getResource("battle.fxml"));
            Scene scene = new Scene(battle , 800 , 600);
            Stage primaryStage = (Stage)membership.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("primaryStage = " + primaryStage);
            System.out.println("scene = " + scene);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    // 일단 끝

}
