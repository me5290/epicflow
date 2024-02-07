package org.example.epicflow.model.dao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.epicflow.model.dto.MonsterDto;

import java.util.ArrayList;

public class MonsterDao extends Dao {


    private MonsterDto monsterDto;

    private MonsterDao() {
    }

    private static MonsterDao monsterDao = new MonsterDao();

    public static MonsterDao getInstance() {
        return monsterDao;
    }

    //몬스터 메소드
    public ArrayList<MonsterDto> monsterinfor() {
        ArrayList<MonsterDto> monsterDtos = new ArrayList<>();
        System.out.println("고블린 사냥터 테스트");
        System.out.println("monsterDtos"+monsterDtos);
        try {
            String sql = "select * from monster";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                monsterDto.setMonsterNo(rs.getInt("monsterno"));
                monsterDto.setMonsterName(rs.getString("monstername"));
                monsterDto.setMonsterMhp(rs.getInt("monstermhp"));
                monsterDto.setMonsterHp(rs.getInt("monsterhp"));
                monsterDto.setMonsterPower(rs.getInt("monsterpower"));
                monsterDto.setMonsterDefence(rs.getInt("monsterdefence"));

                monsterDtos.add(monsterDto);
            }

        } catch (Exception e) {
            System.out.println("db오류" + e);
        }
        return monsterDtos;
    }

}


