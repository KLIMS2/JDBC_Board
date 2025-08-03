package com.ysj.java.board.section.article.repository;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.section.common.repository.Repository;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.process.Request;

import java.util.List;

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
        content = '?';
        """, article.getTitle(), article.getContent());
  }

  private Article getArticle(Data data)
  {
    return rq.dataToArticle(data);
  }

  public Article getArticle(long id)
  {
    Data data = db.sql.select("""
        SELECT *
        FROM article
        WHERE id = ?;
        """, id + "");

    return getArticle(data);
  }

  public Article getLastArticle()
  {
    Data data = db.sql.select("""
        SELECT *
        FROM article
        ORDER BY id DESC
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
    // 데이터 검색 및 정렬
    db.sql.append("""
        SELECT *
        FROM article
        """);

    if(searchType.equals("title"))
    {
      db.sql.append("""
          WHERE title LIKE '%?%'
          """, searchKeyword);
    }
    else if(searchType.equals("content"))
    {
      db.sql.append("""
          WHERE content LIKE '%?%'
          """, searchKeyword);
    }
    else
    {
      db.sql.append("""
          WHERE title LIKE '%?%'
          OR content LIKE '%?%'
          """, searchKeyword);
    }

    if(orderBy.equals("idAsc"))
    {
      db.sql.append("""
        ORDER BY id Asc;
        """);
    }
    else
    {
      db.sql.append("""
        ORDER BY id Desc;
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
