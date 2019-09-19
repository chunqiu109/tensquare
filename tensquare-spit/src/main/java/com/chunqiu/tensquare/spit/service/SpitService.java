package com.chunqiu.tensquare.spit.service;

import com.chunqiu.tensquare.spit.dao.SpitDao;
import com.chunqiu.tensquare.spit.pojo.Spit;
import com.chunqiu.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *功能描述 
 * @author Wangchunqiu
 * @date $
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据父id查询数据
     * @param parentId
     * @param page
     * @param rows
     * @return
     */
    public Page<Spit> findByParentid (String parentId,int page,int rows) {
        Pageable pageable = PageRequest.of(page-1,rows);
        return spitDao.findByParentid(parentId,pageable);
    }

    /**
     * 查找所有数据
     * @return
     */
    public List<Spit> findAll(){
        return  spitDao.findAll();
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 保存数据
     * @param spit
     */
    public void save (Spit spit){
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        //判断父级id是否存在，如果存在，则需要把父级id的数据+1
        if (spit.getParentid() != null && "".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 更新数据
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 根据id删除数据
     * @param id
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    /**
     * 点赞
     * @param spitId
     */
    public void thumbup(String spitId) {
        //方案一 普通方案
        //Spit spit = spitDao.findById(spitId).get();
        //spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) +1);
        //spitDao.save(spit);

        //方案二 使用原生的mongo命令实现自增 db.spit.update({"_id":"1"},{$inc:{thumbup:NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId)); //{"_id":"1"}
        Update update = new Update();
        update.inc("thumbup",1);//{$inc:{thumbup:NumberInt(1)}}
        mongoTemplate.updateFirst(query,update,"spit");

    }
}
