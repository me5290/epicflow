package org.example.epicflow.model.dto;

public class MemberDto {
// ================================ 필드 ================================ //
    int mno;
    String mid;
    String mpw;

// ================================ 생성자 ================================ //
    // ===== 기본 생성자
    public MemberDto(){}
    // ===== 풀 생성자
    public MemberDto(int mno, String mid, String mpw) {
        this.mno = mno;
        this.mid = mid;
        this.mpw = mpw;
    }

    // 로그인 생성자
    public MemberDto(String mid, String mpw) {
        this.mid = mid;
        this.mpw = mpw;
    }
// ================================ 메소드 ================================ //
    // ===== GETTER / SETTER
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
    // ===== TOSTRING
    @Override
    public String toString() {
        return "MemberDto{" +
                "mno=" + mno +
                ", mid='" + mid + '\'' +
                ", mpw='" + mpw + '\'' +
                '}';
    }
}
