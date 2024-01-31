package org.example.epicflow.controller;


import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.epicflow.model.dao.MemberDao;
import org.example.epicflow.model.dto.MemberDto;

public class LoginContrller {

    static int memberNum = 0; // 회원번호 저장ㅇㅇ

    public TextField inputId = null;
    public TextField inputPw = null;


    public void Onlogin(){
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
            // 자바스크립트처럼 경고창 뛰우기
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("아이디 혹은 비밀번호를 입력하지 않았습니다.");
            alert.show();
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
        if (memberNum == 0 ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("존재하지 않는 회원 입니다.");
            alert.show();
            inputId.setText("");
            inputPw.setText("");
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(memberDto.getMid() + "회원님 환영 합니다.");
            alert.show();
            System.out.println(memberNum);
        }
        System.out.println("함수종료");
    }

    public void OnExit(){
        System.out.println("종료합니다.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("종료합니다.");
        alert.show();
        // 종료
        Platform.exit();
    }



}
