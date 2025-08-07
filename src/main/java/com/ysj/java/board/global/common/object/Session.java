package com.ysj.java.board.global.common.object;

import java.util.HashMap;
import java.util.Map;

public class Session
{
  private Map<String, Object> sessionData;

  public Session()
  {
    sessionData = new HashMap<>();
  }

  public void setSession(String key, Object value)
  {
    sessionData.put(key, value);
  }

  public Object getSession(String key)
  {
    return sessionData.get(key);
  }

  public void removeSession(String key)
  {
    sessionData.remove(key);
  }

  public boolean isExistSession(String key)
  {
    return sessionData.containsKey(key);
  }
}
