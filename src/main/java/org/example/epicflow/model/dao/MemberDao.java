package org.example.epicflow.model.dao;

import org.example.epicflow.model.dto.MemberDto;

public class MemberDao extends Dao{
    // 싱글톤
    private MemberDao(){}
    private static MemberDao memberDao = new MemberDao();
    public static MemberDao getInstance(){
        return memberDao;
    }


    // ===== 회원가입 메소드
    public int signUp(MemberDto memberDto){
        System.out.println(memberDto.toString());
        try {
        // ===== SQL 작성
        String sql = "insert into member(mid,mpw)values( ?, ? )";

        // ===== SQL 기재
        ps = con.prepareStatement(sql);
                                                            System.out.println(memberDto.getMid());
                                                            System.out.println(memberDto.getMpw());
        // ===== ? 매개변수 대입
        ps.setString(1,memberDto.getMid());
        ps.setString(2,memberDto.getMpw());

        // ===== SQL 실행
        int count = ps.executeUpdate();
                                                            System.out.println(count);
            // ===== 만약에 INSERT 처리된 레코드가 1개이면 회원가입 성공 0이면 성공
            if(count==1){
                return 0;
            }
            System.out.println("성공");
        }catch (Exception e){
            System.out.println("오류 : " + e);
        }

        // ===== 함수 종료 1이면 실패
        return 1;
    }

    // 아이디 중복검사
    public boolean idCheck(String mid){
        try{
            // 1. SQL 작성한다.
            String sql = "select mid from member where mid = ?";
            // 2. SQL 기재한다.
            ps = con.prepareStatement(sql);
            // ? 매개변수 대입
            ps.setString(1,mid);
            // 3. SQL 실행한다.
            rs = ps.executeQuery();
            // 4. SQL 결과처리
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        // 5. 함수종료
        return false;   // 중복 없음
    }

    
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
