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
import java.io.IOException;


public class LoginController extends MainApplication {

    public static int memberNum; // 회원번호 저장
    public Pane mainVuew;       // 첫 시작 뷰
    public TextField inputId = null;    // 로그인시 아이디 입력칸
    public PasswordField inputPw = null;    // 로그인시 비밀번호 입력칸
    public ImageView LoginIcon;         // 로그인 뷰 이미지
    public Label loginLabel = null;     // 로그인 성공/실패시 텍스트 표시
    public Button loginSbtn;            // 로그인 성공/실패시 뷰
    public Button ok;
    public Button membership;           // 회원가입 클릭 버ㅓ튼
    public Pane memberView;             // 로그인 시 환영 메세지
    public Pane cNamePane;              // 로그인 성공시 캐릭터 생성 뷰
    public TextField cName;             // 로그인 성공시 캐릭터 이름 받기
    public Button generation;           // 캐릭터 생성 버튼
    public Button cancel;               // 캐릭터 생성 뷰 에서 취소버튼

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
            System.out.println("memberNum = " + memberNum);
            // 4. 처리
            if (getMemberNum() == 0 ){
                // Pane 뛰워서 존재하지않는 회원 표시
                memberView.setVisible(true);
                loginLabel.setText("존재하지 않는 회원 입니다.");
                inputId.setText("");
                inputPw.setText("");
                break;
            }else {
                // 로그인 성공 시 DB에 캐릭터 있는지 비교
//                memberView.setVisible(true);
//                loginLabel.setText(memberDto.getMid() + "회원님 환영 합니다.");
//                System.out.println(memberNum);
//                bolean = true;
//                System.out.println("OK버튼" + ok);

                // 캐릭터 존재 여부 확인 메소드
                if (characterFind()){
                    // 캐릭터 있으면 마을 씬 이동
                    villageScene();
                }else {
                    // 캐릭터 없으면 캐릭터 생성 뷰 생성
                    inputId.setText("");
                    inputPw.setText("");
                    System.out.println("캐릭터생성 뷰 보이기");
                    System.out.println(cNamePane);
                    cNamePane.setVisible(true);
                } // 2 if else
                break;
            }// 1  if else 끝
        } // w e
        

    }
    // 로그인 성공! 및 캐릭터 생성 완료 시 마을씬 이동
    public void villageScene(){
        try {
            Parent battle = FXMLLoader.load(getClass().getResource("village.fxml"));
            Scene scene = new Scene(battle, 1300, 498);
            Stage primaryStage = (Stage) membership.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("primaryStage = " + primaryStage);
            System.out.println("scene = " + scene);
        }catch (Exception e){
            System.out.println("villageScene : " + e);
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


    // 캐릭터 유무 확인 메서드
    public boolean characterFind(){
        boolean ch = PlayerDao.getInstance().characterFind(memberNum);
        System.out.println("캐릭터 찾기 ch = " + ch);

        // 캐릭터 있으면 트루
        if(ch){
            return true;
        }
        // 캐릭터 없으면 false
        return false;
    }


    // 캐릭터 생성 버튼 클릭 시
    public void characterGeneration(){
        System.out.println("cName = " + cName.getText());
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPname(cName.getText());
        playerDto.setMno(memberNum);
        System.out.println(playerDto.toString());

        // Dao 에 객체 넘기기
        boolean result = PlayerDao.getInstance().characterGeneration(playerDto);

        // 결과 처리
        if (result){
            // 캐릭터 생성 성공!! 마을로 이동
            villageScene();
        }else {
            System.out.println("캐릭터 생성 실패!");
        }
    }
    // 캐릭터 생성 뷰 에서 취소 클릭 시
    public void characterCancel(){
        System.out.println("캐릭터생성 뷰 안보이기");
        cNamePane.setVisible(false);

    }

    // 일단 끝

}
