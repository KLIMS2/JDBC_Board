package com.ysj.java.board.global.dataBase.element;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector
{
  private String url;
  private String username;
  private String password;
  private String database;

  public Connection conn;

  public DBConnector(String ip, String username, String password, String database)
  {
    // 데이터베이스 연결 정보 설정
    this.username = username;
    this.password = password;
    this.database = database;

    if(ip.equals("localhost"))
    {
      ip = "127.0.0.1";
    }
    this.url = "jdbc:mysql://" + ip + ":3306/" + database +
        "?useSSL=false&autoReconnect=true&serverTimezone=Asia/Seoul&" +
        "characterEncoding=UTF-8&useUnicode=true&allowPublicKeyRetrieval=true";

    // Connection 객체 선언
    conn = null;

    try
    {
      // 1. JDBC 드라이버 로드
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("------------------");
      System.out.println("드라이버 로드 성공!");

      // 2. 데이터베이스 연결 시도
      conn = DriverManager.getConnection(url, username, password);
      System.out.println("데이터베이스 연결 성공!");
      System.out.println("------------------");
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
        System.out.println("------------------");
        System.out.println("데이터베이스 연결 종료!");
        System.out.println("------------------");
      }
    }
    catch (SQLException e)
    {
      System.out.println("연결 종료 중 오류 발생: " + e.getMessage());
    }
  }
}
