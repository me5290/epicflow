package org.example.epicflow.controller;    // 패키지명

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.epicflow.model.dao.MemberDao;
import org.example.epicflow.model.dto.MemberDto;

public class JoinController {   // Class Start

// ================================ 필드 ================================ //
    @FXML
    public TextField mid;   // 아이디
    @FXML
    public TextField mpw;   // 아이디
    @FXML
    public Button loginScreen;  // 로그인 화면 이동

// ================================ 메소드 ================================ //
// ================================ 회원가입 s ================================ //
    // ===== 회원가입 메소드
    public void signUp(){

        // 회원가입 성공 버튼 [ true = 회원가입 성공, false = 회원가입 실패 ]
        boolean btn = false;
        
        String mid = this.mid.getText();
        String mpw = this.mpw.getText();

        MemberDto playerDto = new MemberDto(mid, mpw);

        // 회원가입 실패
            // ==== 아이디와 비밀번호를 입력하지 않았을 때
        if(mid.equals("") && mpw.equals("")){
            Msg("아이디와 비밀번호를 입력해주세요.");

            // ==== 비밀번호를 입력하지 않았을 때
        } else if (!mid.equals("") && mpw.equals("")) {
            Msg("비밀번호를 입력해주세요.");

            // ==== 아이디를 입력하지 않았을 때
        } else if(mid.equals("") && !mpw.equals("")){
            Msg("아이디를 입력해주세요.");

            // ==== 아이디가 사용중일 때
        } else if(MemberDao.getInstance().idCheck(playerDto.getMid())){
            Msg("현재 사용중인 아이디입니다.");
        } else{
            btn = true;
            System.out.println("[ ● 안내 : 회원가입 성공 ● ]");
        }

        if(btn){
            MemberDao.getInstance().signUp(playerDto);
            Msg("회원가입에 성공하였습니다.");
            loginScreen();
        }
    }

    // ===== 회원가입 알림창
    public void Msg(String msgIn){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("[알림]");
        alert.setHeaderText(msgIn);
        alert.show();
    }
// ================================ 회원가입 e ================================ //

// ================================ 화면전환 s ================================ //
    // ===== 회원가입 화면 -> 로그인 화면
    public void loginScreen(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage)loginScreen.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            System.out.println("[ ※ 안내 : 화면전환 오류입니다. ※ ]" + e);
        }
    }
// ================================ 화면전환 e ================================ //
} //  Class End