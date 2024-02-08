package org.example.epicflow.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    protected Dao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/epicflow",
                    "root" , "1234"
            );
            System.out.println("[ ● 안내 : DB 연동 성공 ● ]");
        }catch (Exception e){
            System.out.println("[ ※ 안내 : DB 연동 실패 ※ ]"+e);
        }
    } // m e
} // c e
