package com.ysj.java.board.section.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTO
{
  protected long id;
  protected LocalDateTime regDate;
  protected LocalDateTime updateDate;

  public String getFormatRegDate()
  {
    return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public String getFormatUpdateDate()
  {
    return updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

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
          field.setAccessible(true);
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
          field.setAccessible(true);
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
        findField.setAccessible(true);
        findField.set(this, value);
      }
      catch (IllegalAccessException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}