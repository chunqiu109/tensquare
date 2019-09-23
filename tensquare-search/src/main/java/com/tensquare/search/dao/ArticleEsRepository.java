package com.tensquare.search.dao;

import com.tensquare.search.po.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 持久层
 */
public interface ArticleEsRepository extends ElasticsearchRepository<Article,String> {

    /**
     * 根据标题和内容模糊匹配查询分页列表
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    Page<Article> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);

}
