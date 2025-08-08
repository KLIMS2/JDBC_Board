package com.ysj.java.board.app;

import com.ysj.java.board.global.common.contain.Container;
import com.ysj.java.board.global.common.object.Session;
import com.ysj.java.board.global.intercept.login.LoginIntercept;
import com.ysj.java.board.global.intercept.logout.LogoutIntercept;
import com.ysj.java.board.section.common.controller.Controller;
import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.process.Request;
import com.ysj.java.board.section.common.repository.Repository;

import java.util.Scanner;

import static com.ysj.java.board.global.common.object.Constant.DEFAULT_PROMPT;

public class App
{
  private DB settingDB()
  {
    DB db = new DB(
        "localhost",
        "ysj",
        "asdf1",
        "JDBC_Board");

    Repository.setDB(db);
    Session.setDB(db);

    return db;
  }

  private boolean isIntercept(Request rq)
  {
    LoginIntercept loginIntercept = Container.loginIntercept;
    LogoutIntercept logoutIntercept = Container.logoutIntercept;

    return loginIntercept.run(rq) || logoutIntercept.run(rq);
  }

  public void run()
  {
    System.out.println("--> 자바 프로그램 시작 <--");

    Scanner sc = Container.sc;
    Request rq = Container.rq;
    DB db = settingDB();

    Controller controller;
    String prompt, cmd;

    while(true)
    {
      if(rq.isLogin())
      {
        prompt = rq.getLoginedMember().getUserId();
      }
      else
      {
        prompt = DEFAULT_PROMPT;
      }

      System.out.print(prompt + ") "); cmd = sc.nextLine().trim();
      rq.setUrl(cmd); rq.run();

      if(isIntercept(rq))
      {
        continue;
      }

      controller = rq.getController();

      if(controller == null)
      {
        if(cmd.equals("exit")) // exit
        {
          System.out.println("프로그램을 종료합니다.");
          break;
        }
        else
        {
          System.out.println("잘못된 입력입니다.");
        }
      }
      else
      {
        controller.run();
      }
    }

    db.dbConnector.close();
    sc.close();
    System.out.println("--> 자바 프로그램 끝 <--");
  }
}
