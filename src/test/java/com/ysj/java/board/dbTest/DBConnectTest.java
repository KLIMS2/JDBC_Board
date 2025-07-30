package com.ysj.java.board.dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectTest
{
  public Connection conn;

  public DBConnectTest()
  {
    // 데이터베이스 연결 정보 설정
    String url = "jdbc:mysql://127.0.0.1:3306/JDBC_Board?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true"; // JDBC URL (MySQL 기준)
    String username = "ysj"; // 데이터베이스 사용자 이름
    String password = "asdf1"; // 데이터베이스 비밀번호

    // Connection 객체 선언
     conn = null;

    try
    {
      // 1. JDBC 드라이버 로드
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("드라이버 로드 성공!");

      // 2. 데이터베이스 연결 시도
      conn = DriverManager.getConnection(url, username, password);
      System.out.println("데이터베이스 연결 성공!");

      // 3. 연결 정보 출력
      System.out.println("DBMS: " + conn.getMetaData().getDatabaseProductName());
      System.out.println("버전: " + conn.getMetaData().getDatabaseProductVersion());
      System.out.println("URL: " + conn.getMetaData().getURL());
      System.out.println("사용자명: " + conn.getMetaData().getUserName());

    }
    catch (ClassNotFoundException e)
    {
      // JDBC 드라이버를 찾을 수 없을 때 발생하는 예외 처리
      System.out.println("JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
    }
    catch (SQLException e)
    {
      // 데이터베이스 연결 또는 쿼리 실행 중 발생하는 예외 처리
      System.out.println("데이터베이스 연결 실패: " + e.getMessage());
    }
  }

  public void close()
  {
    try
    {
      if (conn != null && !conn.isClosed())
      {
        conn.close();
        System.out.println("데이터베이스 연결 종료!");
      }
    }
    catch (SQLException e)
    {
      System.out.println("연결 종료 중 오류 발생: " + e.getMessage());
    }
  }
}
