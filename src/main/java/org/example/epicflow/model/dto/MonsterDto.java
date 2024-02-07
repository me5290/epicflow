package org.example.epicflow.model.dto;

public class MonsterDto {
        // ================================ 필드 ================================ //
        private int monsterNo = 0;
        private String monsterName = "슬라임";
        private int monsterMhp = 30;
        private int monsterHp = 30;
        private int monsterPower = 7;
        private int monsterDefence = 2;
        private int dropGold = 10;
        private int dropExp = 30;

        public MonsterDto(){}

        public MonsterDto(int monsterNo, String monsterName, int monsterMhp, int monsterHp, int monsterPower, int monsterDefence, int dropGold, int dropExp) {
                this.monsterNo = monsterNo;
                this.monsterName = monsterName;
                this.monsterMhp = monsterMhp;
                this.monsterHp = monsterHp;
                this.monsterPower = monsterPower;
                this.monsterDefence = monsterDefence;
                this.dropGold = dropGold;
                this.dropExp = dropExp;
        }

        // get/set 시작
        public int getMonsterNo() {
                return monsterNo;
        }

        public void setMonsterNo(int monsterNo) {
                this.monsterNo = monsterNo;
        }

        public String getMonsterName() {
                return monsterName;
        }

        public void setMonsterName(String monsterName) {
                this.monsterName = monsterName;
        }

        public int getMonsterMhp() {
                return monsterMhp;
        }

        public void setMonsterMhp(int monsterMhp) {
                this.monsterMhp = monsterMhp;
        }

        public int getMonsterHp() {
                return monsterHp;
        }

        public void setMonsterHp(int monsterHp) {
                this.monsterHp = monsterHp;
        }

        public int getMonsterPower() {
                return monsterPower;
        }

        public void setMonsterPower(int monsterPower) {
                this.monsterPower = monsterPower;
        }

        public int getMonsterDefence() {
                return monsterDefence;
        }

        public void setMonsterDefence(int monsterDefence) {
                this.monsterDefence = monsterDefence;
        }

        public int getDropGold() {
                return dropGold;
        }

        public void setDropGold(int dropGold) {
                this.dropGold = dropGold;
        }

        public int getDropExp() {
                return dropExp;
        }

        public void setDropExp(int dropExp) {
                this.dropExp = dropExp;
        }
}
