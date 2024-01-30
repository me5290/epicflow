package org.example.epicflow.model.dao;

public class MemberDao {
    // 싱글톤
    private MemberDao(){}
    private static MemberDao memberDao = new MemberDao();
    public static MemberDao getInstance(){
        return memberDao;
    }




}
