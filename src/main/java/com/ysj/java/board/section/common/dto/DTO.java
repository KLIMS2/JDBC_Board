package com.ysj.java.board.section.common.dto;

import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Data
public abstract class DTO
{
  protected long id;
  protected LocalDateTime regDate;
  protected LocalDateTime updateDate;

  public abstract Object getField(String fieldName);

  public abstract void setField(String fieldName, Object value);
}
