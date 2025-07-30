package com.ysj.java.board.global.common.repository;

import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.DBConnector;

public class Repository
{
  protected DB db;

  public Repository()
  {
    db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");
  }

  public DB getDb()
  {
    return db;
  }
}
