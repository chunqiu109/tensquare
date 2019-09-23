package com.tensquare.search.controller;

/**
 *功能描述 
 * @author Wangchunqiu
 * @date $
 */

import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import com.tensquare.search.po.Article;
import com.tensquare.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 保存文章到es中
     * @param article
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Article article){
        articleSearchService.saveArticle(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

}
