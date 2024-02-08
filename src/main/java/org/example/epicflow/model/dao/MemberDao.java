package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.MemberDto;

public class MemberDao extends Dao{

    // ==== 싱글톤
    private MemberDao(){}
    private static MemberDao memberDao = new MemberDao();
    public static MemberDao getInstance(){
        return memberDao;
    }


    // ===== 회원가입 메소드 [ return = 0 성공 , return = 1 실패 ]
    public int signUp(MemberDto memberDto){
        try {
        String sql = "insert into member(mid,mpw)values( ?, ? )";
        ps = con.prepareStatement(sql);
        ps.setString(1,memberDto.getMid());
        ps.setString(2,memberDto.getMpw());
        int count = ps.executeUpdate();
            if(count==1){
                System.out.println("[ ● 안내 : INSERT 성공 ● ]");
                return 0;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : signUp 오류입니다. ※ ]" + e);
        }
        return 1;
    }

    // ===== 아이디 중복검사 [ return = true 중복있음, return = false 중복없음 ]
    public boolean idCheck(String mid){
        try{
            String sql = "select mid from member where mid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,mid);
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("[ ※ 안내 : ID 중복입니다. ※ ]");
                return true;
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : idCheck 오류 입니다. ※ ]"+e);
        }
        System.out.println("[ ● 안내 : ID 중복 없습니다. ● ]");
        return false;
    }

    // ===== 로그인 [ return = findMno(회원아이디), return = 0 회원정보 없음 ]
    public int Onlogin(MemberDto memberDto){
        try {
            String sql = "select * from member where mid = ? and mpw = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1 , memberDto.getMid());
            ps.setString(2 , memberDto.getMpw());
            rs = ps.executeQuery();
            if ( rs.next() ) {
                return findMno(memberDto.getMid());
            }
        }catch (Exception e){
            System.out.println("[ ※ 안내 : Onlogin 오류 입니다. ※ ]"+e);
        }
        return 0;
    }

    // ==== 아이디를 이용한 회원번호 찾기 [ return = 회원번호, return = 0 회원정보 없음 ]
    public int findMno( String mid ){
        try {
            String sql = "select mno from member where mid = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("mno");
            }
        }catch ( Exception e ){
            System.out.println("[ ※ 안내 : fidMno 오류 입니다. ※ ]"+e);
        }
        // 5. 함수종료
        return 0;
    }

}
