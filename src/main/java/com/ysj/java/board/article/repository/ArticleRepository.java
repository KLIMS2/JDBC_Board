package com.ysj.java.board.article.repository;

import com.ysj.java.board.article.dto.Article;
import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.global.common.repository.Repository;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.process.Request;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

  public Article getArticle(Data data)
  {
    Map<String, Object> dataRow = data.getOneData();
    if(dataRow == null)
    {
      return null;
    }

    Article article = new Article(
        (long)dataRow.get("id"),
        (LocalDateTime)dataRow.get("regDate"),
        (LocalDateTime)dataRow.get("updateDate"),
        (String)dataRow.get("title"),
        (String)dataRow.get("content"));

    return article;
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

  public Article getLatestArticle()
  {
    Data data = db.sql.select("""
        SELECT *
        FROM article
        ORDER BY id DESC
        LIMIT 1;
        """);

    return getArticle(data);
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

    List<Map<String, Object>> dataRows = data.getData();
    if(dataRows == null)
    {
      return null;
    }

    // 데이터 리스트 생성
    List<Article> articles = new ArrayList<>();
    for (Map<String, Object> dataRow : dataRows) {
      Article article = new Article(
          (long) dataRow.get("id"),
          (LocalDateTime) dataRow.get("regDate"),
          (LocalDateTime) dataRow.get("updateDate"),
          (String) dataRow.get("title"),
          (String) dataRow.get("content"));

      articles.add(article);
    }

    // 데이터 리스트 정렬
    if(orderBy.equals("idDesc"))
    {
      articles = rq.sortArticlesReverse(articles);
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
