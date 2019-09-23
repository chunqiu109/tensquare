package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleEsRepository;
import com.tensquare.search.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.chunqiu.tensquare.util.IdWorker;

/**
 * 业务层
 */
@Service
public class ArticleSearchService {

    //注入dao
    @Autowired
    private ArticleEsRepository articleEsRepository;
    //注入idworker
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存文章到索引库中
     * @param article
     */
    public void saveArticle(Article article){
        //article.setId(idWorker.nextId()+"");
        articleEsRepository.save(article);
    }

    /**
     * 根据关键字模糊匹配查询文章搜索的分页列表
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findArticleListPageByKeywordsLike(String keywords, int page, int size){
        return  articleEsRepository.findByTitleLikeOrContentLike(keywords,keywords, PageRequest.of(page-1,size));
    }

}
