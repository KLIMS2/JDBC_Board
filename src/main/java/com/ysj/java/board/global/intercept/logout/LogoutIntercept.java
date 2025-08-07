package com.ysj.java.board.global.intercept.logout;

import com.ysj.java.board.global.process.Request;

public class LogoutIntercept
{
  public boolean run(Request rq)
  {
    if(rq.isLogout())
    {
      return false;
    }

    String urlPath = rq.getUrlPath();
    if(urlPath == null || urlPath.isEmpty())
    {
      return false;
    }

    return switch (urlPath) {
      case "/usr/member/login" -> {
        System.out.println("로그아웃 후 이용해주세요.");
        yield true;
      }

      default -> false;
    };
  }
}
