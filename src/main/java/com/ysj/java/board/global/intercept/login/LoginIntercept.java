package com.ysj.java.board.global.intercept.login;

import com.ysj.java.board.global.process.Request;

public class LoginIntercept
{
  public boolean run(Request rq)
  {
    if(rq.isLogin())
    {
      return false;
    }

    String urlPath = rq.getUrlPath();
    if(urlPath == null || urlPath.isEmpty())
    {
      return false;
    }

    return switch (urlPath) {
      case "/usr/article/write",
           "/usr/article/modify",
           "/usr/article/delete",
           "/usr/member/logout",
           "/usr/member/mypage",
           "/usr/member/modify",
           "/usr/member/withdraw" -> {
        System.out.println("로그인 후 이용해주세요.");
        yield true;
      }

      default -> false;
    };
  }
}
