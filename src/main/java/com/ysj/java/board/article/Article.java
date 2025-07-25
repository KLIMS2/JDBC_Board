package com.ysj.java.board.article;

public class Article
{
  public static int lastId;
  public int id;
  public String title;
  public String content;

  static
  {
    lastId = 0;
  }

  public Article(String title, String content)
  {
    this.id = ++lastId;
    this.title = title;
    this.content = content;
  }
}
