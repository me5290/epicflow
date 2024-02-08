package org.example.epicflow.model.dao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.epicflow.model.dto.MonsterDto;
import org.example.epicflow.model.dto.PlayerDto;

import java.util.ArrayList;

public class MonsterDao extends Dao{

    // ==== 싱글톤
    private MonsterDao(){}
    private static MonsterDao monsterDao = new MonsterDao();
    public static MonsterDao getInstance(){ return monsterDao; }

    public ArrayList<MonsterDto> monsterInfor(){
        ArrayList<MonsterDto> monsterDtos = new ArrayList<>();
        try {
            String sql = "select * from monster";

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                MonsterDto monsterDto = new MonsterDto();
                monsterDto.setMonsterNo( rs.getInt("monsterno") );
                monsterDto.setMonsterName( rs.getString("monstername") );
                monsterDto.setMonsterMhp( rs.getInt("monstermhp") );
                monsterDto.setMonsterHp( rs.getInt("monsterhp") );
                monsterDto.setMonsterPower( rs.getInt("monsterpower") );
                monsterDto.setMonsterDefence( rs.getInt("monsterdefence") );
                monsterDto.setDropGold( rs.getInt("dropgold") );
                monsterDto.setDropExp( rs.getInt("dropexp") );

                monsterDtos.add(monsterDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return monsterDtos;
    }
}
