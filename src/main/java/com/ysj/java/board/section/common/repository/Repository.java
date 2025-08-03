package com.ysj.java.board.section.common.repository;

import com.ysj.java.board.global.dataBase.DB;

public abstract class Repository
{
  protected static DB db;

  public static void setDB(DB database)
  {
    db = database;
  }
}
