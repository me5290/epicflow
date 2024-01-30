package org.example.epicflow.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
    protected Connection con; // 1. DB 연동객체
    protected PreparedStatement ps; // 2. 작성된 SQL 가지고 있고, 실행 담당
    protected ResultSet rs; // 3. SQL 실행 결과

    protected Dao(){    // 생성자 : 객체 생성시 초기화 담당
        // - 객체 생성시 DB연동
        try {
            // 1. MYSQL 회사의 JDBC관련된 (Driver)객체를 JVM에 로딩한다 , 불러오기
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 연동된 결과의 (구현체)객체를 Connection 인터페이스에 대입한다
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java",
                    "root",
                    "1234"
            );
            System.out.println("<DB연동 성공>");
        }catch (Exception e){
            System.out.println("<DB연동 실패>"+e);
        }
    }
}
