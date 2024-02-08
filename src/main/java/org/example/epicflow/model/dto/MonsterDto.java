package org.example.epicflow.model.dto;

public class MonsterDto {
// ================================ 필드 ================================ //
        private int monsterNo;                  // 몬스터 번호
        private String monsterName;             // 몬스터 이름
        private int monsterMhp;                 // 몬스터 최대 체력
        private int monsterHp;                  // 몬스터 체력
        private int monsterPower;               // 몬스터 공격력
        private int monsterDefence;             // 몬스터 방어력
        private int dropGold;                   // 몬스터 드랍 골드
        private int dropExp;                    // 몬스터 드랍 경험치

// ================================ 생성자 ================================ //
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

// ================================ 메소드 ================================ //
        // ===== GETTER / SETTER
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
