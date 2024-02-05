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

// ================================ 회원가입 s ================================ //
    @FXML
    public TextField mid;
    @FXML
    public TextField mpw;
    @FXML
    public Button loginScreen;


    // ===== 회원가입 메소드
    public void signUp(){

        // 회원가입 성공 버튼
        boolean btn = false;
        
        String mid = this.mid.getText();
        String mpw = this.mpw.getText();

        // ===== 객체생성
        MemberDto playerDto = new MemberDto(mid, mpw);

        // 회원가입 실패
        if(mid.equals("") && mpw.equals("")){
            Msg("아이디와 비밀번호를 입력해주세요.");
        } else if (!mid.equals("") && mpw.equals("")) {
            Msg("비밀번호를 입력해주세요.");
        } else if(mid.equals("") && !mpw.equals("")){
            Msg("아이디를 입력해주세요.");
        } else if(MemberDao.getInstance().idCheck(playerDto.getMid())){
            Msg("현재 사용중인 아이디입니다.");
        } else{
            btn = true; // true이면 성공
        }

        // 회원가입 성공
        if(btn){
            // ===== 객체 넘기기
            MemberDao.getInstance().signUp(playerDto);

            System.out.println(playerDto.toString());
            System.out.println(this.mid.getText());
            System.out.println(this.mpw.getText());

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
            System.out.println(e);
        }

    }





} //  Class End