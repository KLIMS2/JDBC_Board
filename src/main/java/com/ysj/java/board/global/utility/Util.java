package com.ysj.java.board.global.utility;

import com.ysj.java.board.section.common.dto.DTO;
import com.ysj.java.board.global.dataBase.element.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  public static <T> void swap(List<T> list, int index1, int index2)
  {
    if(list.isEmpty())
    {
      return;
    }

    T temp = list.get(index1);
    list.set(index1, list.get(index2));
    list.set(index2, temp);
  }

  public static <T> List<T> sortReverse(List<T> list)
  {
    if(list.isEmpty())
    {
      return null;
    }

    for(int a = 0; a < list.size() / 2; a++)
    {
      swap(list, a, (list.size() - 1) - a);
    }

    return list;
  }

  public static List<DTO> dataToDtoList(Data data, List<DTO> dtoList)
  {
    Class dtoClass = dtoList.get(0).getClass();
    Field[] superFields = dtoClass.getSuperclass().getDeclaredFields();
    Field[] fields = dtoClass.getDeclaredFields();
    List<Map<String, Object>> dataRows = data.getData();

    if(dataRows == null)
    {
      return null;
    }

    for(int a = 0; a < dataRows.size(); a++)
    {
      for(int b = 0; b < superFields.length; b++)
      {
        String fieldName = superFields[b].getName();
        dtoList.get(a).setField(fieldName, dataRows.get(a).get(fieldName));
      }
      for(int b = 0; b < fields.length; b++)
      {
        String fieldName = fields[b].getName();
        dtoList.get(a).setField(fieldName, dataRows.get(a).get(fieldName));
      }
    }

    return dtoList;
  }
}
