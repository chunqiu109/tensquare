package com.chunqiu.tensquare.base.dao;

import com.chunqiu.tensquare.base.pojo.Label;
import org.springframework.data.jpa.mapping.JpaPersistentProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 功能描述  标签数据访问接口
 *
 * @author Wangchunqiu
 * @date $
 * $
 * JpaRepository :提供了基本的增删改查
 * JpaSpecificationExecutor ： 用于做复杂的条件查询
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
