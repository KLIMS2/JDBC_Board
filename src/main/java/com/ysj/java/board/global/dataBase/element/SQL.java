package com.ysj.java.board.global.dataBase.element;

import com.ysj.java.board.global.common.contain.Container;
import com.ysj.java.board.global.process.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL
{
  private DBConnector dbConnector;
  private Request rq;
  private String sql;

  public SQL(DBConnector dbConnector)
  {
    this.dbConnector = dbConnector;
    rq = Container.rq;
    sql = "";
  }

  public void append(String code, String... args)
  {
    sql += rq.substitution(code, "\\?", args);
  }

  public void run()
  {
    try
    {
      dbConnector.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).execute();
      sql = "";
//      System.out.println("sql query successful!");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void run(String code, String... args)
  {
    sql = rq.substitution(code, "\\?", args);

    try
    {
      dbConnector.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).execute();
      sql = "";
//      System.out.println("sql query successful!");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  public Data select()
  {
    ResultSet rs;

    try
    {
      rs = dbConnector.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).executeQuery();
      sql = "";
//      System.out.println("sql query successful!");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }

    return new Data(rs);
  }

  public Data select(String code, String... args)
  {
    sql = rq.substitution(code, "\\?", args);
    ResultSet rs;

    try
    {
      rs = dbConnector.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).executeQuery();
      sql = "";
//      System.out.println("sql query successful!");
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }

    return new Data(rs);
  }
}
