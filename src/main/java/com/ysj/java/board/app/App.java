package com.ysj.java.board.app;

import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.section.common.controller.Controller;
import com.ysj.java.board.global.dataBase.DB;
import com.ysj.java.board.global.process.Request;

import java.util.Scanner;

public class App
{
  public void run()
  {
    System.out.println("--> 자바 프로그램 시작 <--");

    Scanner sc = Container.sc;
    Request rq = Container.rq;
    DB db = Container.articleRepository.getDb();
    Controller controller;
    String cmd;

    while(true)
    {
      System.out.print("명령) "); cmd = sc.nextLine();
      rq.setUrl(cmd); rq.run();

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
