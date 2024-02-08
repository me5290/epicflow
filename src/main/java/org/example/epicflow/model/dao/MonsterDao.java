package org.example.epicflow.model.dao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.epicflow.model.dto.MonsterDto;

import java.util.ArrayList;

public class MonsterDao extends Dao{

    // ==== 싱글톤
    private MonsterDao(){}
    private static MonsterDao monsterDao = new MonsterDao();
    public static MonsterDao getInstance(){ return monsterDao; }

}
