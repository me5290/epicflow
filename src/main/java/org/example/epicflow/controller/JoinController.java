package org.example.epicflow.controller;    // 패키지명

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.epicflow.model.dao.MemberDao;
import org.example.epicflow.model.dto.MemberDto;

public class JoinController {   // Class Start

// ================================ 회원가입 s ================================ //
    @FXML
    public TextArea mid;
    @FXML
    public TextArea mpw;

    // ===== 회원가입 메소드
    public void signUp(){
        String mid = this.mid.getText();
        String mpw = this.mpw.getText();

        // ===== 객체생성
        MemberDto playerDto = new MemberDto(mid, mpw);

        // ===== 객체 넘기기
        MemberDao.getInstance().signUp(playerDto);

//        System.out.println(playerDto.toString());
//        System.out.println(this.mid);
//        System.out.println(this.mpw);
//        System.out.println(this.mid.getText());
//        System.out.println(this.mpw.getText());

        Msg("회원가입에 성공하였습니다.");
    }

    // ===== 회원가입 알림창
    public void Msg(String msgIn){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("[알림]");
        alert.setHeaderText(msgIn);
        alert.show();
    }
// ================================ 회원가입 e ================================ //
} //  Class End