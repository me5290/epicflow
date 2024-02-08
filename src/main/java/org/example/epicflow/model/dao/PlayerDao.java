package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.PlayerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerDao extends Dao{

    // ==== 싱글톤
    private PlayerDao(){}
    private static PlayerDao playerDao = new PlayerDao();
    public static PlayerDao getInstance(){
        return playerDao;
    }

    // ==== PLAYER 저장 메소드 [ return = 플레이어 정보 ]
    public ArrayList<PlayerDto> playerInfor(){
        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
        try {
            String sql = "select * from player";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                PlayerDto playerDto = new PlayerDto();
                playerDto.setPno( rs.getInt("pno") );
                playerDto.setPname( rs.getString("pname") );
                playerDto.setMhp( rs.getInt("mhp") );
                playerDto.setHp( rs.getInt("hp") );
                playerDto.setMmp( rs.getInt("mmp") );
                playerDto.setMp( rs.getInt("mp") );
                playerDto.setJob( rs.getInt("job") );
                playerDto.setLevel( rs.getInt("level") );
                playerDto.setExp( rs.getInt("exp") );
                playerDto.setMoney( rs.getInt("money") );
                playerDto.setStatpoint( rs.getInt("statpoint") );
                playerDto.setPower( rs.getInt("power") );
                playerDto.setDefence( rs.getInt("defence") );
                playerDto.setSkillpower( rs.getInt("skillpower") );
                playerDto.setStr( rs.getInt("str") );
                playerDto.setDex( rs.getInt("dex") );
                playerDto.setWis( rs.getInt("wis") );
                playerDto.setEva( rs.getInt("eva") );
                playerDto.setSpd( rs.getInt("spd") );
                playerDto.setMno( rs.getInt("mno") );
                playerDtos.add(playerDto);
                System.out.println("[ ● 안내 : 캐릭터 불러오기 성공 ● ]");
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : playerInfor 실패 입니다. ※ ]" + e);
        }
        return playerDtos;
    }

    // ==== 캐릭터 유무 확인 메서드 [ return = true 캐릭터 있음, return = 캐릭터 없음 ]
    public boolean characterFind(int memberNum){
        try {
            String sql = "select * from player where mno = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,memberNum);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : characterFind 오류 입니다. ※ ]" + e );
        }
        return false;
    }

    // ==== 캐릭터 생성 버튼 클릭 시 이름과 회원번호 저장 [ return = 1 저장 성공, return = 실패 ]
    public boolean characterGeneration(PlayerDto playerDto){
        try {
            String sql = "insert into player(pname,mno)values( ?, ? )";
            ps = con.prepareStatement(sql);
            ps.setString(1,playerDto.getPname());
            ps.setInt(2,playerDto.getMno());
            int count = ps.executeUpdate();
            if (count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : characterGeneration 오류 입니다. ※ ]" + e );
        }
        // 0 이면 실패
        return false;
    }
    // ==== 스탯 찍으면 db 저장 메서드 [ return = true 성공, return = true 실패 ]
    public boolean playerstatpoint(PlayerDto playerDto){
        try {
            String sql = "update player set statpoint = ?, str = ?, dex = ?, wis = ? where mno = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,playerDto.getStatpoint());
            ps.setInt(2,playerDto.getStr());
            ps.setInt(3,playerDto.getDex());
            ps.setInt(4,playerDto.getWis());
            ps.setInt(5,playerDto.getMno());
            int counts = ps.executeUpdate();
            if(counts == 1){
                System.out.println("[ ● 안내 : STAT DB 업데이트 성공 ● ]");
                return true;
            }
        } catch (Exception e) {
            System.out.println("[ ※ 안내 : playerstatpoint 오류 입니다. ※ ]"+e);
        }
        return false;
    }

    // ==== 현재 캐릭터 돈 조회 메소드 [ return = playerDto ]
    public PlayerDto playerMoneySc(int memberNum){
        PlayerDto playerDto = new PlayerDto();
        try {
            String sql = "select * from player where mno = ?";
            ps.setInt(1,memberNum);
            rs = ps.executeQuery();
            if(rs.next()){
                playerDto.setMoney(rs.getInt("money"));
                playerDto.setMhp(rs.getInt("mhp"));
                playerDto.setMmp(rs.getInt("mmp"));
                return playerDto;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : playerMoneySc 오류 입니다. ※ ]"+e);
        }
        return null;
    }

    // ==== 여관 에서 숙박 버튼 클릭시 이벤트
    public void useMtBtnOn(int memberNum , int pMoney ,int Mhp , int Mmp){
        try {
            String sql = "update player set money = ? , hp = ? , mp = ? where mno = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,pMoney);
            ps.setInt(2,Mhp);
            ps.setInt(3,Mmp);
            ps.setInt(4,memberNum);
            int count = ps.executeUpdate();
            if (count == 1){
                System.out.println("[ ● 안내 : useMtBtnOn DB 업데이트 성공 ● ]");
                return;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : useMtBtnOn 오류 입니다. ※ ]"+ e);
        }
        System.out.println("[ ※ 안내 : useMtBtnOn 회복 실패 입니다. ※ ]");
    } // m e
} // c e

