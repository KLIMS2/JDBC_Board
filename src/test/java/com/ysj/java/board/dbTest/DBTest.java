package com.ysj.java.board.dbTest;

import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.DBConnector;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DBTest
{
  private DB db;

  @BeforeAll
  public void beforeAll()
  {
    db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");
    System.out.println("DB connect successful!");

    db.sql.run("""
        DROP DATABASE IF EXISTS test;
        """);
    db.sql.run("""
        CREATE DATABASE test;
        """);
    db.sql.run("""
        USE test;
        """);
    db.sql.run("""
        CREATE TABLE article(
        	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        	regDate DATETIME NOT NULL,
        	updateDate DATETIME NOT NULL,
        	title CHAR(100) NOT NULL,
        	content TEXT NOT NULL
        );
        """);
  }

  @AfterAll
  public void afterAll()
  {
    db.dbConnector.close();
    System.out.println("DB close successful!");
  }

  @Test
  @DisplayName("test data create")
  public void t1()
  {
    final int DATA_NUM = 10;

    for(int a = 1; a <= DATA_NUM; a++)
    {
      String title = "title" + a, content = "content" + a;

      db.sql.run("""
        INSERT INTO article
        SET regDate = NOW(),
        updateDate = NOW(),
        title = '%s',
        content = '%s';
        """.formatted(title, content));
    }

    System.out.println("data create successful!");
  }

  @Test
  @DisplayName("delete test data")
  public void t2()
  {
    db.sql.run("""
        TRUNCATE article;
        """);

    System.out.println("data delete successful!");
  }
}
