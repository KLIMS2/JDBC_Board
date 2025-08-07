package com.ysj.java.board.global.common.contain;

import com.ysj.java.board.global.common.object.Session;
import com.ysj.java.board.global.intercept.login.LoginIntercept;
import com.ysj.java.board.global.intercept.logout.LogoutIntercept;
import com.ysj.java.board.section.article.controller.ArticleController;
import com.ysj.java.board.section.article.repository.ArticleRepository;
import com.ysj.java.board.section.article.service.ArticleService;
import com.ysj.java.board.global.process.Request;
import com.ysj.java.board.section.member.controller.MemberController;
import com.ysj.java.board.section.member.repository.MemberRepository;
import com.ysj.java.board.section.member.service.MemberService;

import java.util.Scanner;

public class Container
{
  public static Scanner sc;
  public static Session session;
  public static Request rq;
  public static LoginIntercept loginIntercept;
  public static LogoutIntercept logoutIntercept;

  public static ArticleRepository articleRepository;
  public static ArticleService articleService;
  public static ArticleController articleController;

  public static MemberRepository memberRepository;
  public static MemberService memberService;
  public static MemberController memberController;

  static
  {
    sc = new Scanner(System.in);
    session = new Session();
    rq = new Request();
    loginIntercept = new LoginIntercept();
    logoutIntercept = new LogoutIntercept();

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();

    memberRepository = new MemberRepository();
    memberService = new MemberService();
    memberController = new MemberController();
  }
}
