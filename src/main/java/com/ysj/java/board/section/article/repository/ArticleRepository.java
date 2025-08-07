package com.ysj.java.board.section.article.repository;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.global.common.contain.Container;
import com.ysj.java.board.section.common.repository.Repository;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.process.Request;

import java.util.List;

import static com.ysj.java.board.global.common.object.Constant.SEARCHKEYWORD_WRITER;

public class ArticleRepository extends Repository
{
  private Request rq;

  public ArticleRepository()
  {
    rq = Container.rq;
  }

  public void createArticle(Article article)
  {
    db.sql.run("""
        INSERT INTO article
        SET regDate = NOW(),
        updateDate = NOW(),
        title = '?',
        content = '?',
        memberId = ?;
        """, article.getTitle(), article.getContent(), article.getMemberId() + "");
  }

  private Article getArticle(Data data)
  {
    return rq.dataToArticle(data);
  }

  public Article getArticle(long id)
  {
    Data data = db.sql.select("""
        SELECT A.*, M.nickname AS `writer`
        FROM article AS A
        INNER JOIN _member AS M
        ON A.memberId = M.id
        WHERE A.id = ?;
        """, id + "");

    return getArticle(data);
  }

  public Article getLastArticle()
  {
    Data data = db.sql.select("""
        SELECT A.*, M.nickname AS `writer`
        FROM article AS A
        INNER JOIN _member AS M
        ON A.memberId = M.id
        ORDER BY A.id DESC
        LIMIT 1;
        """);

    return getArticle(data);
  }

  public long getLastId()
  {
    return getLastArticle().getId();
  }

  public List<Article> getArticles(String orderBy, String searchType, String searchKeyword)
  {
    // 데이터 검색
    db.sql.append("""
        SELECT A.*, M.nickname AS `writer`
        FROM article AS A
        INNER JOIN _member AS M
        ON A.memberId = M.id
        """);

    String[] searchTypes = searchType.split(",");
    if(searchTypes[0].equals(SEARCHKEYWORD_WRITER))
    {
      db.sql.append("""
          WHERE M.nickname LIKE '%?%'
          """, searchKeyword);
    }
    else
    {
      db.sql.append("""
          WHERE A.? LIKE '%?%'
          """, searchTypes[0], searchKeyword);
    }

    for(int a = 1; a < searchTypes.length; a++)
    {
      if(searchTypes[a].equals(SEARCHKEYWORD_WRITER))
      {
        db.sql.append("""
          OR M.nickname LIKE '%?%'
          """, searchKeyword);
      }
      else
      {
        db.sql.append("""
          OR A.? LIKE '%?%'
          """, searchTypes[a], searchKeyword);
      }
    }

    // 데이터 정렬
    if(orderBy.equals("idAsc"))
    {
      db.sql.append("""
        ORDER BY A.id Asc;
        """);
    }
    else
    {
      db.sql.append("""
        ORDER BY A.id Desc;
        """);
    }

    Data data = db.sql.select();

    // 데이터 리스트 생성
    List<Article> articles = rq.dataToArticles(data);
    if(articles == null)
    {
      return null;
    }

    return articles;
  }

  public void deleteArticle(Article article)
  {
    db.sql.run("""
        DELETE FROM article
        WHERE id = ?;
        """, article.getId() + "");
  }

  public void updateArticle(Article article)
  {
    db.sql.run("""
        UPDATE article
        SET updateDate = NOW(),
        title = '?',
        content = '?'
        WHERE id = ?;
        """, article.getTitle(), article.getContent(), article.getId() + "");
  }
}
