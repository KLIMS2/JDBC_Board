package com.ysj.java.board.article.dto;

import com.ysj.java.board.global.common.dto.DTO;
import lombok.Data;

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
}
