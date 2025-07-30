package com.ysj.java.board.global.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class DTO
{
  protected long id;
  protected LocalDateTime regDate;
  protected LocalDateTime updateDate;
}
