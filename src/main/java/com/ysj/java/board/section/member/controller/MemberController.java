package com.ysj.java.board.section.member.controller;

import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.global.process.Request;
import com.ysj.java.board.section.common.controller.Controller;
import com.ysj.java.board.section.member.dto.Member;
import com.ysj.java.board.section.member.service.MemberService;

import java.util.Scanner;

public class MemberController implements Controller
{
  private MemberService memberService;
  private Scanner sc;
  private Request rq;

  public MemberController()
  {
    memberService = Container.memberService;
    sc = Container.sc;
    rq = Container.rq;
  }

  public void run()
  {
    String urlPath = rq.getUrlPath();

    if(urlPath.equals("/usr/member/join")) // join
    {
      doJoin();
    }
    else if(urlPath.equals("/usr/member/login")) // login
    {
      doLogin();
    }
    else if(urlPath.equals("/usr/member/logout")) // logout
    {
//      doLogout();
    }
    else if(urlPath.equals("/usr/member/mypage")) // mypage
    {
//      doMypage();
    }
    else if(urlPath.equals("/usr/member/modify")) // modify
    {
//      doModify();
    }
    else if(urlPath.equals("/usr/member/withdraw")) // withdraw
    {
//      doWithdraw();
    }
    else
    {
      System.out.println("잘못된 입력입니다.");
    }
  }

  // join
  private void doJoin()
  {
    System.out.println("> 회원 가입");

    String userId, password, verifyPassword, nickname;

    while (true)
    {
      System.out.print("아이디: ");
      userId = sc.nextLine().trim();

      if(userId.isEmpty())
      {
        System.out.println("아이디를 입력해 주세요.");
        continue;
      }

      Member member = memberService.getMember(userId);
      if(member != null)
      {
        System.out.println("중복된 아이디입니다.");
        continue;
      }

      break;
    }

    while (true)
    {
      System.out.print("비밀번호: ");
      password = sc.nextLine().trim();

      if(password.isEmpty())
      {
        System.out.println("비밀번호를 입력해 주세요.");
        continue;
      }

      break;
    }

    while (true)
    {
      System.out.print("비밀번호 확인: ");
      verifyPassword = sc.nextLine().trim();

      if(verifyPassword.isEmpty())
      {
        System.out.println("확인용 비밀번호를 입력해 주세요.");
        continue;
      }

      if( !verifyPassword.equals(password) )
      {
        System.out.println("비밀번호가 다릅니다.");
        continue;
      }

      break;
    }

    while (true)
    {
      System.out.print("이름: ");
      nickname = sc.nextLine().trim();

      if(nickname.isEmpty())
      {
        System.out.println("이름을 입력해 주세요.");
        continue;
      }

      break;
    }

    Member member = new Member(userId, password, nickname);
    memberService.createMember(member);

    Member newMember = memberService.getLastMember();
    System.out.printf("%s님의 회원 가입이 되었습니다.\n", newMember.getUserId());
  }

  // login
  private void doLogin()
  {
    System.out.println("> 로그인");

    String userId, password;
    Member member;

    while (true)
    {
      System.out.print("아이디: ");
      userId = sc.nextLine().trim();

      if(userId.isEmpty())
      {
        System.out.println("아이디를 입력해 주세요.");
        continue;
      }

      member = memberService.getMember(userId);
      if(member == null)
      {
        System.out.println("존재하지 않는 아이디입니다.");
        continue;
      }

      break;
    }

    final int MAX_COUNT = 5;
    int count = 0;
    while (true)
    {
      if(count >= MAX_COUNT)
      {
        System.out.println("비밀번호 입력 횟수를 초과하였습니다. 다시 로그인을 해주세요.");
        return;
      }

      System.out.printf("비밀번호(%d / %d): ", MAX_COUNT - count, MAX_COUNT);
      password = sc.nextLine().trim();

      if(password.isEmpty())
      {
        System.out.println("비밀번호를 입력해 주세요.");
        continue;
      }

      if( !password.equals(member.getPassword()) )
      {
        System.out.println("비밀번호가 일치하지 않습니다.");
        count++;
        continue;
      }

      break;
    }

    System.out.printf("%s님 로그인 성공!\n", member.getUserId());
  }
}