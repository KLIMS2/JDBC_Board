package com.ysj.java.board.global.dataBase.element;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data
{
  protected List<Map<String, Object>> data;

  public Data()
  {
    data = new ArrayList<>();
  }

  public Data(ResultSet rs)
  {
    this();
    setData(rs);
  }

  public void setData(ResultSet rs)
  {
    try
    {
      ResultSetMetaData resultSetMetaData = rs.getMetaData();
      int columnCount = resultSetMetaData.getColumnCount();

      while(rs.next())
      {
        Map<String, Object> map = new HashMap<>();

        for(int a = 1; a <= columnCount; a++)
        {
          String columnName = resultSetMetaData.getColumnLabel(a);
          Object columnValue = rs.getObject(columnName);
          map.put(columnName, columnValue);
        }

        data.add(map);
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  public List<Map<String, Object>> getData()
  {
    if(data.isEmpty())
    {
      return null;
    }

    return data;
  }

  public Map<String, Object> getOneData()
  {
    if(data.isEmpty())
    {
      return null;
    }

    return data.get(0);
  }
}
