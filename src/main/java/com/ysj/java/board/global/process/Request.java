package com.ysj.java.board.global.process;

import com.ysj.java.board.article.dto.Article;
import com.ysj.java.board.global.common.Container;
import com.ysj.java.board.global.common.controller.Controller;
import com.ysj.java.board.global.utility.GenericUtil;
import com.ysj.java.board.global.utility.Util;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
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
                return Container.articleRepository.getLatestArticle().getId();
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

    public List<Article> sortArticlesReverse(List<Article> articles)
    {
        GenericUtil<Article> genericUtil = new GenericUtil<>();
        return genericUtil.sortReverse(articles);
    }
}
