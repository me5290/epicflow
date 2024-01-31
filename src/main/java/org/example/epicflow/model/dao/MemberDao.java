package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.MemberDto;

public class MemberDao extends Dao{
    // 싱글톤
    private MemberDao(){}
    private static MemberDao memberDao = new MemberDao();
    public static MemberDao getInstance(){
        return memberDao;
    }





    
    
    // 회원가입
    
    
    
    
    
    
    
    
    // 로그인
    public int Onlogin(MemberDto memberDto){
        try {
            // 1. sql 작성
            String sql = "select * from member where mid = ? and mpw = ? ";
            // 2. sql 기재
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setString(1 , memberDto.getMid());
            ps.setString(2 , memberDto.getMpw());
            // 3 sql 실행
            rs = ps.executeQuery();
            if ( rs.next() ) {  return findMno(memberDto.getMid()); }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // 0. 아이디를 이용한 회원번호 찾기
    public int findMno( String mid ){
        try {
            // 1. SQL 작성한다.
            String sql = "select mno from member where mid = ?";
            // 2. SQL 기재한다.
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setString(1, mid);
            // 3. SQL 실행한다.
            rs = ps.executeQuery();
            // 4. SQL 결과처리
            if (rs.next()) {
                // rs.next() : 다음 레코드 이동
                // rs.get타입( 호출할 필드번호 or 필드이름 ) : 현재 레코드의 필드 값 호출
                return rs.getInt("mno");
            }
        }catch ( Exception e ){  System.out.println(e);   }
        // 5. 함수종료
        return 0;
    }

}
