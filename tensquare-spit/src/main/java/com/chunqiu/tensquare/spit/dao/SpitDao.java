package com.chunqiu.tensquare.spit.dao;

import com.chunqiu.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 功能描述
 *
 * @author Wangchunqiu
 * @date $
 * $
 * @return $
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    //根据父id查询数据
    public Page<Spit> findByParentid(String parentId, Pageable pageable);

}
