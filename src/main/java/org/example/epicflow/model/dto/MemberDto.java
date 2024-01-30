package org.example.epicflow.model.dto;

public class MemberDto {

    private int mno;
    private String mid;
    private String mpw;

    // 기본 생성자
    public MemberDto() {}

    // 로그인 생성자
    public MemberDto(String mid, String mpw) {
        this.mid = mid;
        this.mpw = mpw;
    }

    // get / set
    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMpw() {
        return mpw;
    }

    public void setMpw(String mpw) {
        this.mpw = mpw;
    }
}
