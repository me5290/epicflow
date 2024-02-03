package org.example.epicflow.model.dto;

public class PlayerDto {
    private int pno;        // 캐릭터번호
    private String pname;   // 캐릭터이름
    private int mhp;        // 최대 체력
    private int hp;         // 체력
    private int mmp;        // 최대 마나
    private int mp;         // 마나
    private int job;        // 직업
    private int level;      // 레벨
    private int exp;        // 경험치
    private int money;      // 돈
    private int statpoint;  // 스텟 포인트
    private int power;      // 공격력
    private int defence;    // 방어력
    private int str;        // 힘
    private int dex;        // 민첩
    private int wis;        // 지능
    private int eva;        // 회피
    private int spd;        // 속도
    private int mno;        // 회원 번호

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getMhp() {
        return mhp;
    }

    public void setMhp(int mhp) {
        this.mhp = mhp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMmp() {
        return mmp;
    }

    public void setMmp(int mmp) {
        this.mmp = mmp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getStatpoint() {
        return statpoint;
    }

    public void setStatpoint(int statpoint) {
        this.statpoint = statpoint;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getEva() {
        return eva;
    }

    public void setEva(int eva) {
        this.eva = eva;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "pno=" + pno +
                ", pname='" + pname + '\'' +
                ", mhp=" + mhp +
                ", hp=" + hp +
                ", mmp=" + mmp +
                ", mp=" + mp +
                ", job=" + job +
                ", level=" + level +
                ", exp=" + exp +
                ", power=" + power +
                ", defence=" + defence +
                ", str=" + str +
                ", dex=" + dex +
                ", wis=" + wis +
                ", eva=" + eva +
                ", spd=" + spd +
                ", mno=" + mno +
                '}';
    }
}
