package com.ysj.java.board.global.process;

import com.ysj.java.board.section.article.dto.Article;
import com.ysj.java.board.section.common.dto.DTO;
import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.section.common.controller.Controller;
import com.ysj.java.board.global.dataBase.element.Data;
import com.ysj.java.board.global.utility.Util;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Request
{
    private String url;
    private String urlPath;
    private Map<String, String> params;

    public Request()
    {
        url = null;
        params = null;
    }

    public void run()
    {
        if(url.isEmpty())
        {
            return;
        }

        urlPath = Util.getUrlPath(url);
        params = Util.getParams(url);
    }

    public long getLongParam(String key, long defaultValue)
    {
        if(params == null)
        {
            return defaultValue;
        }
        else if(!params.containsKey(key))
        {
            return defaultValue;
        }

        try
        {
            return Long.parseLong(params.get(key));
        }
        catch (NumberFormatException e)
        {
            if(params.get(key).equals("LAST"))
            {
                return Container.articleRepository.getLastId();
            }

            return defaultValue;
        }
    }

    public String getStringParam(String key, String defaultValue)
    {
        if(params == null)
        {
            return defaultValue;
        }
        else if(!params.containsKey(key))
        {
            return defaultValue;
        }

        return params.get(key);
    }

    public Controller getController()
    {
        if(urlPath == null)
        {
            return null;
        }

        if(urlPath.startsWith("/usr/article"))
        {
            return Container.articleController;
        }
        else
        {
            return null;
        }
    }

    public String substitution(String string, String before, String... after)
    {
        return Util.substitution(string, before, after);
    }

    public <T> List<T> sortReverse(List<T> articles)
    {
        return Util.sortReverse(articles);
    }

    public List<Article> dataToArticles(Data data)
    {
        List<Map<String, Object>> list = data.getData();
        if(list == null)
        {
            return null;
        }

        List<DTO> dtoList = new ArrayList<>();
        int length = list.size();

        for(int a = 0; a < length; a++)
        {
            dtoList.add(new Article());
        }

        List<DTO> rs = Util.dataToDtoList(data, dtoList);
        List<Article> articleList = new ArrayList<>();

        for(int a = 0; a < length; a++)
        {
            articleList.add((Article) rs.get(a));
        }

        return articleList;
    }

    public Article dataToArticle(Data data)
    {
        List<Article> rs = dataToArticles(data);
        if(rs == null)
        {
            return null;
        }

        return rs.get(0);
    }
}
