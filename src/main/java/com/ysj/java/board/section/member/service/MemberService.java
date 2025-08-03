package com.ysj.java.board.section.member.service;

import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.section.member.dto.Member;
import com.ysj.java.board.section.member.repository.MemberRepository;

public class MemberService
{
  private MemberRepository memberRepository;

  public MemberService()
  {
    memberRepository = Container.memberRepository;
  }

  public void createMember(Member member)
  {
    memberRepository.createMember(member);
  }

  public Member getLastMember()
  {
    return memberRepository.getLastMember();
  }

  public Member getMember(String userId)
  {
    return memberRepository.getMember(userId);
  }
}
