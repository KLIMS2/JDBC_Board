package com.ysj.java.board.section.article.dto;

import com.ysj.java.board.section.common.dto.DTO;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Data
public class Article extends DTO
{
  private String title;
  private String content;

  public Article(long id, LocalDateTime regDate, LocalDateTime updateDate, String title, String content)
  {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.title = title;
    this.content = content;
  }

  public Article(String title, String content)
  {
    this(0, null, null, title, content);
  }

  public Article()
  {
    this(0, null, null, null, null);
  }

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
