package com.ysj.java.board.article.service;

import com.ysj.java.board.article.controller.ArticleController;
import com.ysj.java.board.article.dto.Article;
import com.ysj.java.board.article.repository.ArticleRepository;
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

  public Article getLatestArticle()
  {
    return articleRepository.getLatestArticle();
  }

  public Article getArticle(long id)
  {
    return articleRepository.getArticle(id);
  }

  public List<Article> getArticles(String orderBy, String searchKeyword)
  {
    return articleRepository.getArticles(orderBy, searchKeyword);
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
