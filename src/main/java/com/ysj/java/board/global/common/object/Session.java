package com.ysj.java.board.global.common.object;

import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.process.Request;
import com.ysj.java.board.section.member.dto.Member;

import static com.ysj.java.board.global.common.object.Constant.SESSIONKEYWORD_LOGIN;

public class Session
{
  private static DB db;

  public static void setDB(DB database)
  {
    db = database;
  }

  public void setSession(String key, Object value)
  {
    if(key.equals(SESSIONKEYWORD_LOGIN))
    {
      Member member = (Member) value;

      db.sql.run("""
          UPDATE _session
          SET loginedMember = ?;
          """, member.getId() + "");
    }
  }

  public Object getSession(String key)
  {
    if(key.equals(SESSIONKEYWORD_LOGIN))
    {
      Data data = db.sql.select("""
          SELECT M.*
          FROM _session AS S
          INNER JOIN _member AS M
          ON S.loginedMember = M.id;
          """);

      Request rq = new Request();
      return rq.dataToMember(data);
    }

    return null;
  }

  public void removeSession(String key)
  {
    if(key.equals(SESSIONKEYWORD_LOGIN))
    {
      db.sql.run("""
          UPDATE _session
          SET loginedMember = 0;
          """);
    }
  }

  public boolean isExistSession(String key)
  {
    if(key.equals(SESSIONKEYWORD_LOGIN))
    {
      Data data = db.sql.select("""
          SELECT loginedMember != 0 AS `isExist`
          FROM _session;
          """);

      int isExist = (int) data.getOneData().get("isExist");
      if(isExist == 1)
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    return false;
  }
}
