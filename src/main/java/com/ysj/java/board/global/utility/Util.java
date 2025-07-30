package com.ysj.java.board.global.utility;

import com.ysj.java.board.article.dto.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Util
{
  public static String getUrlPath(String url)
  {
    String[] urlParts = url.split("\\?", 2);

    if(urlParts[0].isEmpty())
    {
      return null;
    }

    return urlParts[0];
  }

  public static Map<String, String> getParams(String url)
  {
    Map<String, String> params = new HashMap<>();
    String[] urlParts = url.split("\\?", 2);

    if(urlParts.length == 1)
    {
      return null;
    }

    String[] querys = urlParts[1].split("&");
    for(String query : querys)
    {
      String[] queryParts = query.split("=", 2);
      String paramKey, paramVal;

      if(queryParts.length == 1 || queryParts[0].isEmpty())
      {
        continue;
      }

      paramKey = queryParts[0];
      paramVal = queryParts[1];

      params.put(paramKey, paramVal);
    }

    if(params.isEmpty())
    {
      return null;
    }

    return params;
  }

  public static String substitution(String string, String before, String... after)
  {
    String rs = "";
    String[] strings = string.split(before);

    if(after.length == 0 || strings.length == 1)
    {
      return string;
    }

    for(int a = 0; a < strings.length - 1; a++)
    {
      String afterString;
      if(a >= after.length)
      {
        afterString = after[after.length - 1];
      }
      else
      {
        afterString = after[a];
      }

      rs += (strings[a] + afterString);
    }
    rs += strings[strings.length - 1];

    return rs;
  }
}
