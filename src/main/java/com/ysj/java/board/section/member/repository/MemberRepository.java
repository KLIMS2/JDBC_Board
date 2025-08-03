package com.ysj.java.board.section.member.repository;

import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.process.Request;
import com.ysj.java.board.section.common.repository.Repository;
import com.ysj.java.board.section.member.dto.Member;

public class MemberRepository extends Repository
{
  private Request rq;

  public MemberRepository()
  {
    rq = Container.rq;
  }

  public void createMember(Member member)
  {
    db.sql.run("""
        INSERT INTO _member
        SET regDate = NOW(),
        updateDate = NOW(),
        userId = '?',
        password = '?',
        nickname = '?';
        """, member.getUserId(), member.getPassword(), member.getNickname());
  }

  private Member getMember(Data data)
  {
    return rq.dataToMember(data);
  }

  public Member getMember(String userId)
  {
    Data data = db.sql.select("""
        SELECT *
        FROM _member
        WHERE userId = '?';
        """, userId);

    return getMember(data);
  }

  public Member getLastMember()
  {
    Data data = db.sql.select("""
        SELECT *
        FROM _member
        ORDER BY id DESC
        LIMIT 1;
        """);

    return getMember(data);
  }
}
