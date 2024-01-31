package org.example.epicflow.model.dao;

public class BattleDao extends Dao{
    // 싱글톤
    private BattleDao(){}
    private static BattleDao battleDao = new BattleDao();
    public static BattleDao getInstance(){
        return battleDao;
    }


}
