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

  public List<Article> getArticles(String orderBy, String searchKeyword)
  {
    // 데이터 검색
    Data data = db.sql.select("""
        SELECT *
        FROM article
        WHERE title LIKE '%?%'
        OR content LIKE '%?%';
        """, searchKeyword);

    // 데이터 리스트 생성
    List<Article> articles = rq.dataToArticles(data);
    if(articles == null)
    {
      return null;
    }

    // 데이터 리스트 정렬
    if(orderBy.equals("idDesc"))
    {
      articles = rq.sortReverse(articles);
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
