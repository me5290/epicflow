package org.example.epicflow.model.dto;

public class PlayerDto {
    private int pno;
    private String pname;
    private int mhp;
    private int hp;
    private int mmp;
    private int mp;
    private int job;
    private int level;
    private int exp;
    private int power;
    private int defence;
    private int str;
    private int dex;
    private int wis;
    private int eva;
    private int spd;
    private int mno;

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
