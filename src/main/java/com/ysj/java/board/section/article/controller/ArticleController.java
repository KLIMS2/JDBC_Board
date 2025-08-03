package com.ysj.java.board.section.article.controller;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.section.article.service.ArticleService;
import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.section.common.controller.Controller;
import com.ysj.java.board.global.process.Request;

import java.util.List;
import java.util.Scanner;

import static com.ysj.java.board.global.common.Constant.*;

public class ArticleController implements Controller
{
  private ArticleService articleService;
  private Scanner sc;
  private Request rq;

  public ArticleController()
  {
    articleService = Container.articleService;
    sc = Container.sc;
    rq = Container.rq;
  }

  public void run()
  {
    String urlPath = rq.getUrlPath();

    if(urlPath.equals("/usr/article/write")) // write
    {
      doWrite();
    }
    else if(urlPath.equals("/usr/article/detail")) // detail
    {
      doDetail();
    }
    else if(urlPath.equals("/usr/article/list")) // list
    {
      doList();
    }
    else if(urlPath.equals("/usr/article/delete")) // delete
    {
      doDelete();
    }
    else if(urlPath.equals("/usr/article/modify")) // modify
    {
      doModify();
    }
    else
    {
      System.out.println("잘못된 입력입니다.");
    }
  }

  // write
  private void doWrite()
  {
    System.out.println("> 게시물 생성");

    String title, content;

    while (true)
    {
      System.out.print("제목: ");
      title = sc.nextLine().trim();

      if(title.isEmpty())
      {
        System.out.println("제목을 입력해 주세요.");
        continue;
      }

      break;
    }

    while (true)
    {
      System.out.print("내용: ");
      content = sc.nextLine().trim();

      if(content.isEmpty())
      {
        System.out.println("내용을 입력해 주세요.");
        continue;
      }

      break;
    }

    Article article = new Article(title, content);
    articleService.createArticle(article);

    Article newArticle = articleService.getLastArticle();
    System.out.printf("%d번 게시물이 생성되었습니다.\n", newArticle.getId());
  }

  //detail
  private void doDetail()
  {
    long id = rq.getLongParam("id", ERROR);
    if(id == ERROR)
    {
      System.out.println("id가 존재하지 않거나 정수 형태가 아닙니다.");
      return;
    }

    Article article = articleService.getArticle(id);
    if(article == null)
    {
      System.out.println("존재하지 않는 게시물입니다.");
      return;
    }

    System.out.println("> 게시물 상세 조회");

    System.out.printf("id: %d\n", article.getId());
    System.out.printf("regDate: %s\n", article.getFormatRegDate());
    System.out.printf("updateDate: %s\n", article.getFormatUpdateDate());
    System.out.printf("title: %s\n", article.getTitle());
    System.out.printf("content: %s\n", article.getContent());
  }

  // list
  private void doList()
  {
    String orderBy = rq.getStringParam("orderBy", DEFAULT_ORDERBY);
    if( !(orderBy.equals("idAsc") || orderBy.equals("idDesc")) )
    {
      System.out.println("orderBy의 값으로 (idAsc, idDesc) 중에 하나를 선택하지 않았습니다.");
      return;
    }

    String searchType = rq.getStringParam("searchType", DEFAULT_SEARCHTYPE);
    if( !(searchType.equals("title,content") || searchType.equals("title") || searchType.equals("content")) )
    {
      System.out.println("searchType의 값으로 (title,content | title | content) 중에 하나를 선택하지 않았습니다.");
      return;
    }

    String searchKeyword = rq.getStringParam("searchKeyword", DEFAULT_SEARCHKEYWORD);

    List<Article> articleList = articleService.getArticles(orderBy, searchType, searchKeyword);
    if(articleList == null)
    {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.printf("> 게시물 리스트(총 %d개)\n", articleList.size());

    System.out.println("(id | title | regDate | updateDate)");
    articleList.forEach(article -> {
      System.out.printf("%d | %s | %s | %s\n",
          article.getId(),
          article.getTitle(),
          article.getFormatRegDate(),
          article.getFormatUpdateDate());
    });
  }

  // delete
  private void doDelete()
  {
    long id = rq.getLongParam("id", ERROR);
    if(id == ERROR)
    {
      System.out.println("id가 존재하지 않거나 정수 형태가 아닙니다.");
      return;
    }

    Article article = articleService.getArticle(id);
    if(article == null)
    {
      System.out.println("존재하지 않는 게시물입니다.");
      return;
    }

    System.out.println("> 게시물 삭제");

    articleService.deleteArticle(article);
    System.out.println(article.getId() + "번 게시물이 삭제되었습니다.");
  }

  // modify
  private void doModify()
  {
    long id = rq.getLongParam("id", ERROR);
    if(id == ERROR)
    {
      System.out.println("id가 존재하지 않거나 정수 형태가 아닙니다.");
      return;
    }

    Article article = articleService.getArticle(id);
    if(article == null)
    {
      System.out.println("존재하지 않는 게시물입니다.");
      return;
    }

    System.out.println("> 게시물 수정");

    String title, content;

    while (true)
    {
      System.out.print("제목: ");
      title = sc.nextLine().trim();

      if(title.isEmpty())
      {
        System.out.println("제목을 입력해 주세요.");
        continue;
      }

      if(title.equals(QUERYKEYWORD_STAY))
      {
        title = article.getTitle();
        break;
      }

      break;
    }

    while (true)
    {
      System.out.print("내용: ");
      content = sc.nextLine().trim();

      if(content.isEmpty())
      {
        System.out.println("내용을 입력해 주세요.");
        continue;
      }

      if(content.equals(QUERYKEYWORD_STAY))
      {
        content = article.getContent();
        break;
      }

      break;
    }

    article.setTitle(title);
    article.setContent(content);
    articleService.updateArticle(article);
    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
  }
}