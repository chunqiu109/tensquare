package com.chunqiu.tensquare.base.service;

import com.chunqiu.tensquare.base.dao.LabelDao;
import com.chunqiu.tensquare.base.pojo.Label;
import com.chunqiu.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *功能描述 
 * @author Wangchunqiu
 * @date $
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    public Label findById(String id){
        return  labelDao.findById(id).get();
    }

    /**
     * 添加标签
     * @param label
     */
    public void add(Label label){
        label.setId(idWorker.nextId()+"");//设置id
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void delete(String id){
        labelDao.deleteById(id);
    }

}
