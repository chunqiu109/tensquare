package com.chunqiu.tensquare.spit.service;

import com.chunqiu.tensquare.spit.dao.SpitDao;
import com.chunqiu.tensquare.spit.pojo.Spit;
import com.chunqiu.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
