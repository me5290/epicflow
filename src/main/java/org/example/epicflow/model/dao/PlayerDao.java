package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.PlayerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerDao extends Dao{
    // 싱글톤
    private PlayerDao(){}
    private static PlayerDao playerDao = new PlayerDao();
    public static PlayerDao getInstance(){
        return playerDao;
    }

    // DB에 있는 플레이어 정보 DTO에 저장
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

                // str에 따른 스텟 변경
                for(int i = 1; i <= playerDto.getStr(); i++){
                    if(playerDto.getStr() != 0){
                        playerDto.setMhp(playerDto.getMhp()+5);
                        playerDto.setPower(playerDto.getPower()+2);
                        playerDto.setDefence(playerDto.getDefence()+1);
                    }
                }
                // dex에 따른 스텟 변경
                for(int i = 1; i <= playerDto.getDex(); i++){
                    if(playerDto.getDex() != 0){
                        playerDto.setEva(playerDto.getEva()+2);
                        playerDto.setSpd(playerDto.getSpd()+2);
                        playerDto.setPower(playerDto.getPower()+1);
                    }
                }
                // wis에 따른 스텟 변경
                for(int i = 1; i <= playerDto.getWis(); i++){
                    if(playerDto.getWis() != 0){
                        playerDto.setMmp(playerDto.getMmp()+5);
                        playerDto.setEva(playerDto.getEva()+1);
                        playerDto.setSkillpower(playerDto.getSkillpower()+3);
                    }
                }

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

    // 현재 로그인 캐릭터 돈 조회 메서드
    public PlayerDto playerMoneySc(int memberNum){
        // 회원번호 , 객체 생성
        PlayerDto playerDto = new PlayerDto();
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

            // 캐릭터를 찾으면 계정의 돈을 반환
            if(rs.next()){
                playerDto.setMoney(rs.getInt("money"));
                playerDto.setMhp(rs.getInt("mhp"));
                playerDto.setMmp(rs.getInt("mmp"));
                return playerDto;
            }
        }catch (Exception e){
            System.out.println("playerMoneySc DAO" + e);
        }
        // 캐릭터를 못찾았을 경우 오류 코드 발생 시키기
        return null;
    }

    // 여관 에서 숙박 버튼 클릭시 이벤트
    public void useMtBtnOn(int memberNum , int pMoney ,int Mhp , int Mmp){
        System.out.println("숙박 버튼 클릭 DAO");
        System.out.println(memberNum);
        System.out.println(pMoney);
        System.out.println(Mhp);
        System.out.println(Mmp);
        try {
            // sql 작성
            String sql = "update player set money = ? , hp = ? , mp = ? where mno = ?";
            // sql 기재
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setInt(1,pMoney);
            ps.setInt(2,Mhp);
            ps.setInt(3,Mmp);
            ps.setInt(4,memberNum);
            // sql 실행
            int count = ps.executeUpdate();
            if (count == 1){
                System.out.println("여관 회복 성공!");
                return;
            }
        }catch (Exception e){
            System.out.println("useMtBtnOn DAO" + e);
        }
        System.out.println("여관 회복 실패");

    } // m e

} // c e

