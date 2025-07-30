package com.ysj.java.board.dbTest;

import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.DBConnector;
import com.ysj.java.board.global.dataBase.element.Data;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DBSelectTest
{
  public static void main(String[] args) {
    DB db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");

    Data data = db.sql.select("""
        SELECT *
        FROM article;
        """);

    List<Map<String, Object>> list = data.getData();

    list.forEach(dataRow -> {
      System.out.printf("id: %d\n", (long)dataRow.get("id"));
      System.out.printf("regDate: %s\n", (LocalDateTime)dataRow.get("regDate"));
      System.out.printf("updateDate: %s\n", (LocalDateTime)dataRow.get("updateDate"));
      System.out.printf("title: %s\n", (String)dataRow.get("title"));
      System.out.printf("content: %s\n", (String)dataRow.get("content"));
    });

    db.dbConnector.close();
  }
}
