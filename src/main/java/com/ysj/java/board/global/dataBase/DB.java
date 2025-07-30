package com.ysj.java.board.global.dataBase;

import com.ysj.java.board.global.dataBase.element.DBConnector;
import com.ysj.java.board.global.dataBase.element.SQL;

public class DB
{
  public DBConnector dbConnector;
  public SQL sql;

  public DB(String ip, String username, String password, String database)
  {
    dbConnector = new DBConnector(ip, username, password, database);
    sql = new SQL(dbConnector);
  }
}
