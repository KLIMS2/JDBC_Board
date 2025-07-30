package com.ysj.java.board.dbTest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInsertTest
{
  public static void main(String[] args) throws SQLException {
    DBConnectTest db = new DBConnectTest();

    String sql = """
        DROP TABLE IF EXISTS article;
        """;
    PreparedStatement psmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    psmt.execute();

    sql = """ 
        CREATE TABLE article(
        	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
        	regDate DATETIME NOT NULL,
        	updateDate DATETIME NOT NULL,
        	title CHAR(100) NOT NULL,
        	content TEXT NOT NULL
        );
        """;
    psmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    psmt.execute();

    String title = "title1", content = "content1";
    sql = """
        INSERT INTO article
        SET regDate = NOW(),
        updateDate = NOW(),
        title = '%s',
        content = '%s';
        """.formatted(title, content);
    db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).execute();

    db.close();
  }
}
