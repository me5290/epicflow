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

    // 플레이어 정보 저장
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
                playerDto.setPower( rs.getInt("power") );
                playerDto.setDefence( rs.getInt("defence") );
                playerDto.setStr( rs.getInt("str") );
                playerDto.setDex( rs.getInt("dex") );
                playerDto.setWis( rs.getInt("wis") );
                playerDto.setEva( rs.getInt("eva") );
                playerDto.setSpd( rs.getInt("spd") );
                playerDto.setMno( rs.getInt("mno") );

                playerDtos.add(playerDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return playerDtos;
    }

    // 캐릭터 유무 확인 메서드
    public boolean characterFind(int memberNum){
        try {
            // 1. sql 작성
            String sql = "select * from player where mno = ?";
            // 2. sql 기재
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setInt(1,memberNum);
            // sql 실행
            rs = ps.executeQuery();
            // System.out.println("rs = " + rs);

            // 캐릭터를 찾으면 true 반환
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("characterFind : " + e);
        }
        // Player DB 에서 회원 번호 없으면 (캐릭터 없는것) false 반환
        return false;

    }

    //회원 가입 후 캐릭터 생성 버튼 클릭시 메서드
    public boolean characterGeneration(PlayerDto playerDto){
        try {
            // sql 작성
            String sql = "insert into player(pname,mno)values( ?, ? )";
            // sql 기재
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setString(1,playerDto.getPname());
            ps.setInt(2,playerDto.getMno());
            // sql 실행
            int count = ps.executeUpdate();
            System.out.println("캐릭터 생성 count = " + count);

            // DB에 INSERT 처리된 레코드가 1개이면 성공 0 이면 실패
            if (count == 1){ return true; }
        }catch (Exception e){
            System.out.println("characterGeneration : " + e );
        }
        // 0 이면 실패
        return false;
    }
}

