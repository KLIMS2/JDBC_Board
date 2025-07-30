package com.ysj.java.board.global.common;

import com.ysj.java.board.article.controller.ArticleController;
import com.ysj.java.board.article.repository.ArticleRepository;
import com.ysj.java.board.article.service.ArticleService;
import com.ysj.java.board.global.process.Request;

import java.util.Scanner;

public class Container
{
  public static Scanner sc;
  public static Request rq;

  public static ArticleRepository articleRepository;
  public static ArticleService articleService;
  public static ArticleController articleController;

  static
  {
    sc = new Scanner(System.in);
    rq = new Request();

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();
  }
}
