package org.example.epicflow.model.dao;

import org.example.epicflow.controller.BattleController;
import org.example.epicflow.controller.PlayerController;
import org.example.epicflow.model.dto.PlayerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class BattleDao extends Dao{
    // ==== 싱글톤
    private BattleDao(){}
    private static BattleDao battleDao = new BattleDao();
    public static BattleDao getInstance(){
        return battleDao;
    }

    // ==== 전투 끝날 시 플레이어 현재 상태 저장 [ return = true 성공, return = true 실패 ]
    public boolean playerNowInfor(PlayerDto playerDto){
        try {
            String sql = "update player set hp = ?, level = ?, exp = ?, statpoint = ?, money = ? where mno = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, playerDto.getHp());
            ps.setInt(2, playerDto.getLevel());
            ps.setInt(3, playerDto.getExp());
            ps.setInt(4, playerDto.getStatpoint());
            ps.setInt(5, playerDto.getMoney());
            ps.setInt(6, playerDto.getMno());
            int count = ps.executeUpdate();
            if(count == 1){
                System.out.println("[ ● 안내 : 캐릭터 현재상태 저장 성공 ● ]");
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : playerNowInfor 오류 입니다. ※ ]" + e);
        }
        return false;
    }

    // ==== 전투 끝날 시 플레이어 현재 상태 저장 [ return = true 성공, return = true 실패 ]
    public boolean playerMaxInfor(PlayerDto playerDto){
        try {
            String sql = "update player set mhp = ?, mmp = ? where mno = ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, playerDto.getMhp());
            ps.setInt(2, playerDto.getMmp());
            ps.setInt(3, playerDto.getMno());
            int count = ps.executeUpdate();
            if(count == 1){
                System.out.println("[ ● 안내 : 캐릭터 현재상태 저장 성공 ● ]");
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : playerMaxInfor 오류 입니다. ※ ]" + e);
        }
        return false;
    }

}
