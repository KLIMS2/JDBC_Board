package com.ysj.java.board.section.member.dto;

import com.ysj.java.board.section.common.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends DTO
{
  private String userId;
  private String password;
  private String nickname;
}
