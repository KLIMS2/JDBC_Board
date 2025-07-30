package com.ysj.java.board.asdf;

import com.ysj.java.board.article.dto.Article;
import com.ysj.java.board.global.process.Request;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class Asdf {
  private List<Article> list;

  @BeforeAll
  public void beforeAll()
  {
    list = new ArrayList<>();

    for(int a = 1; a <= 4; a++)
    {
      list.add(new Article(a + "", a + ""));
    }

    list.forEach(article -> {
      System.out.println(article);
    });
    System.out.println();
  }

  @Test
  @DisplayName("list reverse")
  public void t1()
  {
    Request rq = new Request();
    rq.sortArticlesReverse(list);
  }

  @AfterAll
  public void afterAll()
  {
    list.forEach(article -> {
      System.out.println(article);
    });
  }
}
