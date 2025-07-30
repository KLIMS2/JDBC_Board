package com.ysj.java.board.dbTest;

import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.DBConnector;

public class DBDeleteTest
{
  public static void main(String[] args)
  {
    DB db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");

    db.sql.run("""
        DELETE FROM article;
        """);

    db.dbConnector.close();
  }
}
