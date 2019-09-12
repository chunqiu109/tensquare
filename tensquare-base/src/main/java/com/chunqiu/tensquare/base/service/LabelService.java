package com.chunqiu.tensquare.base.service;

import com.chunqiu.tensquare.base.dao.LabelDao;
import com.chunqiu.tensquare.base.pojo.Label;
import com.chunqiu.tensquare.entity.PageResult;
import com.chunqiu.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    /**
     * 条件查询标签
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label) {

        return labelDao.findAll(new Specification<Label>(){
            /**
             *
             * @param root  根对象，也就是要把条件封装到那个对象中，where列名 = label.getid
             * @param query  分装的是查询关键字，比如group by,order by
             * @param cb    封装条件对象,如果直接返回null,表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList();

                //添加条件：按照labelname模糊查询
                if (label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");  //where labelname like "%小明%"
                    list.add(predicate);
                }

                //添加条件：查找符合state的数据
                if (label.getState() != null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class),label.getState());  //where state = "1"
                    list.add(predicate);
                }

                //new 一个数组作为最终返回值的条件
                Predicate[] pre = new Predicate[list.size()];
                //吧list转成数组
                list.toArray(pre);
                return cb.and(pre); //where labelname like "%小明%" and state = "1"
            }
        });
    }

    /**
     * 条件分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    public Page<Label> pageSearch(Label label, int page, int pageSize) {
        //封装分页对象
        Pageable pageable = PageRequest.of(page-1, pageSize);

        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root  根对象，也就是要把条件封装到那个对象中，where列名 = label.getid
             * @param query 分装的是查询关键字，比如group by,order by
             * @param cb    封装条件对象,如果直接返回null,表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList();

                //添加条件：按照labelname模糊查询
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");  //where labelname like "%小明%"
                    list.add(predicate);
                }

                //添加条件：查找符合state的数据
                if (label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());  //where state = "1"
                    list.add(predicate);
                }

                //new 一个数组作为最终返回值的条件
                Predicate[] pre = new Predicate[list.size()];
                //把list转成数组
                list.toArray(pre);
                return cb.and(pre); //where labelname like "%小明%" and state = "1"
            }
        }, pageable);
    }
}
