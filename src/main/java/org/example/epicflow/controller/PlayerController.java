package org.example.epicflow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import org.example.epicflow.model.dto.PlayerDto;
import org.example.epicflow.model.dao.BattleDao;
import org.example.epicflow.model.dao.PlayerDao;

import java.util.ArrayList;

public class PlayerController {
    // 싱글톤
    private PlayerController(){}
    private static PlayerController playerController = new PlayerController();
    public static PlayerController getInstance(){
        return playerController;
    }

    // 플레이어 정보 저장
    public ArrayList<PlayerDto> playerInfor(){
        ArrayList<PlayerDto> result = PlayerDao.getInstance().playerInfor();

        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).toString());
        }

        return result;
    }
}
