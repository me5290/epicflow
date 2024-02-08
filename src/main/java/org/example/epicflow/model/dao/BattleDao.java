package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.PlayerDto;

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
                System.out.println("[ ● 안내 : 캐릭터 현재상태 저장 성공 ● ]");
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : playerMaxInfor 오류 입니다. ※ ]" + e);
        }
        return false;
    }
}
