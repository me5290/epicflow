package org.example.epicflow.model.dao;

import org.example.epicflow.controller.BattleController;
import org.example.epicflow.controller.PlayerController;
import org.example.epicflow.model.dto.PlayerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class BattleDao extends Dao{
    // 싱글톤
    private BattleDao(){}
    private static BattleDao battleDao = new BattleDao();
    public static BattleDao getInstance(){
        return battleDao;
    }

    // 플레이어 현재 상태 저장
    // update 테이블명 set 수정할필드명 = 수정할값, 수정할필드명 = 수정할값;
    // 수정 해야할 필드
    // 플레이어 현재 체력, 레벨, 경험치, 포인트, 마나
    public boolean playerNowInfor(PlayerDto playerDto){
        try {
            String sql = "update player set mhp = ? , hp = ? , mmp = ? , mp = ? , level = ?, exp = ? , money = ? , statpoint = ? , power = ? , defence = ? , skillpower = ? , str = ? , dex = ? , wis = ? , eva = ? , spd = ? where mno = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, playerDto.getMhp());
            ps.setInt(2, playerDto.getHp());
            ps.setInt(3, playerDto.getMmp());
            ps.setInt(4, playerDto.getMp());
            ps.setInt(5, playerDto.getLevel());
            ps.setInt(6, playerDto.getExp());
            ps.setInt(7, playerDto.getMoney());
            ps.setInt(8, playerDto.getStatpoint());
            ps.setInt(9, playerDto.getPower());
            ps.setInt(10, playerDto.getDefence());
            ps.setInt(11, playerDto.getSkillpower());
            ps.setInt(12, playerDto.getStr());
            ps.setInt(13, playerDto.getDex());
            ps.setInt(14, playerDto.getWis());
            ps.setInt(15, playerDto.getEva());
            ps.setInt(16, playerDto.getSpd());
            ps.setInt(17, playerDto.getMno());
            int count = ps.executeUpdate();
            if(count == 1){
                System.out.println("!!!!!!!!!!!!!!!!!DB에 업데이트 성공입니다!!!!!!!!!!!!!!!!!!");
                System.out.println(playerDto);
                return true;
            }
        }catch (Exception e){
            System.out.println("오류납니다 : " + e);
        }
        return false;
    }
}
