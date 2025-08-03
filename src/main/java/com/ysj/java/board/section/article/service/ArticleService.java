package com.ysj.java.board.section.article.service;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.section.article.repository.ArticleRepository;
import com.ysj.java.board.global.common.Container;

import java.util.List;

public class ArticleService
{
  private ArticleRepository articleRepository;

  public ArticleService()
  {
    articleRepository = Container.articleRepository;
  }

  public void createArticle(Article article)
  {
    articleRepository.createArticle(article);
  }

  public Article getLastArticle()
  {
    return articleRepository.getLastArticle();
  }

  public Article getArticle(long id)
  {
    return articleRepository.getArticle(id);
  }

  public List<Article> getArticles(String orderBy, String searchType, String searchKeyword)
  {
    return articleRepository.getArticles(orderBy, searchType, searchKeyword);
  }

  public void deleteArticle(Article article)
  {
    articleRepository.deleteArticle(article);
  }

  public void updateArticle(Article article)
  {
    articleRepository.updateArticle(article);
  }
}
