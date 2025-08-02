package com.ysj.java.board.asdf;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.global.process.Request;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
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

    for(int a = 1; a <= 5; a++)
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
    rq.sortReverse(list);
  }

  @Test
  @DisplayName("Field test")
  public void t2()
  {
    Article article = new Article("title", "content");
    Field[] fields = Article.class.getDeclaredFields();
    Field[] superFields = Article.class.getSuperclass().getDeclaredFields();

    Field superField = superFields[0];
    article.setField(superField.getName(), 1L);
    superField = superFields[1];
    article.setField(superField.getName(), LocalDateTime.now());
    superField = superFields[2];
    article.setField(superField.getName(), LocalDateTime.now());
    System.out.println("id: " + article.getId());
    System.out.println("regDate: " + article.getRegDate());
    System.out.println("updateDate: " + article.getUpdateDate());

    for(Field field : fields)
    {
      article.setField(field.getName(), "new");
      System.out.println("title: " + article.getTitle());
      System.out.println("content: " + article.getContent());
    }
  }

  @AfterAll
  public void afterAll()
  {
    list.forEach(article -> {
      System.out.println(article);
    });
  }
}
