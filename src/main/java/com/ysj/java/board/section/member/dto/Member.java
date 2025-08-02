package com.ysj.java.board.section.member.dto;

import com.ysj.java.board.section.common.dto.DTO;

import java.lang.reflect.Field;

public class Member extends DTO
{


  @Override
  public Object getField(String fieldName)
  {
    Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
    Field[] fields = this.getClass().getDeclaredFields();

    for(Field field : superFields)
    {
      if(field.getName().equals(fieldName))
      {
        try
        {
          return field.get(this);
        }
        catch (IllegalAccessException e)
        {
          throw new RuntimeException(e);
        }
      }
    }

    for(Field field : fields)
    {
      if(field.getName().equals(fieldName))
      {
        try
        {
          return field.get(this);
        }
        catch (IllegalAccessException e)
        {
          throw new RuntimeException(e);
        }
      }
    }

    return null;
  }

  @Override
  public void setField(String fieldName, Object value)
  {
    Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
    Field[] fields = this.getClass().getDeclaredFields();
    Field findField = null;

    for(Field field : superFields)
    {
      if(field.getName().equals(fieldName))
      {
        findField = field;
        break;
      }
    }

    for(Field field : fields)
    {
      if(field.getName().equals(fieldName))
      {
        findField = field;
        break;
      }
    }

    if(findField != null)
    {
      try
      {
        findField.set(this, value);
      }
      catch (IllegalAccessException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
