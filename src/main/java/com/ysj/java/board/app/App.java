package com.ysj.java.board.app;

import com.ysj.java.board.article.Article;
import com.ysj.java.board.global.Container;

import java.util.Scanner;

public class App
{
  public void run()
  {
    System.out.println("--> 자바 프로그램 시작 <--");

    Scanner sc = Container.sc;
    String input;

    while(true)
    {
      System.out.print("명령) "); input = sc.nextLine();

      if(input.equals("/usr/article/write")) // write
      {
        doWrite(sc);
      }
      else if(input.equals("exit")) // exit
      {
        System.out.println("프로그램을 종료합니다.");
        break;
      }
      else
      {
        System.out.println("잘못된 입력입니다.");
      }
    }

    System.out.println("--> 자바 프로그램 끝 <--");
  }

  private void doWrite(Scanner sc)
  {
    System.out.println("> 게시물 생성");

    String title, content;

    while (true)
    {
      System.out.print("제목: ");
      title = sc.nextLine();

      if(title.trim().isEmpty())
      {
        System.out.println("제목을 입력해 주세요.");
        continue;
      }

      break;
    }

    while (true)
    {
      System.out.print("내용: ");
      content = sc.nextLine();

      if(content.trim().isEmpty())
      {
        System.out.println("내용을 입력해 주세요.");
        continue;
      }

      break;
    }

    Article newArticle = new Article(title, content);

    System.out.printf("%d번 게시물이 생성되었습니다.\n", newArticle.id);
  }
}
