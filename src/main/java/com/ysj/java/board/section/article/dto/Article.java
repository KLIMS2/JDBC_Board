package com.ysj.java.board.section.article.dto;

import com.ysj.java.board.section.common.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article extends DTO
{
  private String title;
  private String content;
  private long memberId;
  private String writer;
}
