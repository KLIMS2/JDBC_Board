package com.ysj.java.board.dbTest;

import com.ysj.java.board.global.common.object.Session;
import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.global.common.contain.Container;
import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.section.member.dto.Member;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.ysj.java.board.global.common.object.Constant.SESSIONKEYWORD_LOGIN;

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
//    t1();
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
        title = '?',
        content = '?';
        """, title, content);

      db.sql.append("""
          INSERT INTO article
          SET regDate = NOW(),
          updateDate = NOW(),
          """);
      db.sql.append("title = '?',", title);
      db.sql.append("content = '?';", content);
      db.sql.run();
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

  @Test
  @DisplayName("dataToDto test")
  public void t3()
  {
    Data data = db.sql.select("""
        SELECT *
        FROM article
        LIMIT 2;
        """);

    List<Article> article = Container.rq.dataToArticles(data);
    System.out.println(article.get(1).getTitle());
    System.out.println(article.get(0).getContent());
    System.out.println(article.get(0).getRegDate());

    Article article1 = Container.rq.dataToArticle(data);
    System.out.println(article1.getContent());
  }

  @Test
  @DisplayName("session test")
  void t4()
  {
    DB db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");
    Session.setDB(db);

    Session session = new Session();
    Member member = new Member("user1", "1234", "user1");
    member.setId(1L); member.setRegDate(null); member.setUpdateDate(null);

    session.setSession(SESSIONKEYWORD_LOGIN, member);

    Member member1 = (Member) session.getSession(SESSIONKEYWORD_LOGIN);
    System.out.println(member1.getUserId());
    System.out.println(member1.getPassword());
    System.out.println(member1.getFormatRegDate());
    System.out.println(member1.getFormatUpdateDate());

    System.out.println(session.isExistSession(SESSIONKEYWORD_LOGIN));

//    session.removeSession(SESSIONKEYWORD_LOGIN);
//    System.out.println(session.isExistSession(SESSIONKEYWORD_LOGIN));
  }
}
