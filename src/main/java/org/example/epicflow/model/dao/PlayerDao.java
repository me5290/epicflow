package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.PlayerDto;

import java.util.ArrayList;

public class PlayerDao extends Dao{
    // 싱글톤
    private PlayerDao(){}
    private static PlayerDao playerDao = new PlayerDao();
    public static PlayerDao getInstance(){
        return playerDao;
    }

    PlayerDto playerDto = new PlayerDto();

    // DB에 있는 플레이어 정보 DTO에 저장
    public ArrayList<PlayerDto> playerInfor(){
        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
        try {
            String sql = "select * from player";

            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                playerDto.setPno( rs.getInt("pno") );
                playerDto.setPname( rs.getString("pname") );
                playerDto.setMhp( rs.getInt("mhp") );
                playerDto.setHp( rs.getInt("hp") );
                playerDto.setMmp( rs.getInt("mmp") );
                playerDto.setMp( rs.getInt("mp") );
                playerDto.setJob( rs.getInt("job") );
                playerDto.setLevel( rs.getInt("level") );
                playerDto.setExp( rs.getInt("exp") );
                playerDto.setPower( rs.getInt("power") );
                playerDto.setDefence( rs.getInt("defence") );
                playerDto.setStr( rs.getInt("str") );
                playerDto.setDex( rs.getInt("dex") );
                playerDto.setWis( rs.getInt("wis") );
                playerDto.setEva( rs.getInt("eva") );
                playerDto.setSpd( rs.getInt("spd") );
                playerDto.setMno( rs.getInt("mno") );

                playerState();

                playerDtos.add(playerDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return playerDtos;
    }

    public void playerState(){
        for(int i = 0; i <= playerDto.getStr(); i++){
            if(playerDto.getStr() != 0){
                playerDto.setHp(playerDto.getHp()+5);
                playerDto.setPower(playerDto.getPower()+2);
                playerDto.setDefence(playerDto.getDefence()+1);
            }
        }
        for(int i = 0; i <= playerDto.getDex(); i++){
            if(playerDto.getDex() != 0){
                playerDto.setEva(playerDto.getEva()+2);
                playerDto.setSpd(playerDto.getSpd()+2);
                playerDto.setPower(playerDto.getPower()+1);
            }
        }
        for(int i = 0; i <= playerDto.getWis(); i++){
            if(playerDto.getWis() != 0){
                playerDto.setMp(playerDto.getMp()+5);
                playerDto.setEva(playerDto.getEva()+1);
            }
        }
    }
}

