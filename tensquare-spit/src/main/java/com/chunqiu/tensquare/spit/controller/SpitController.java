package com.chunqiu.tensquare.spit.controller;

import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import com.chunqiu.tensquare.spit.pojo.Spit;
import com.chunqiu.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *功能描述 
 * @author Wangchunqiu
 * @date $
 */
@RestController
@RequestMapping(value = "/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功！",spitService.findAll());
    }

    /**
     * 根据id查询数据
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"根据id查询成功！",spitService.findById(id));
    }

    /**
     * 根据id删除
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功！");
    }

    /**
     * 更新数据
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Spit spit){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功！");
    }

    /**
     * 添加数据
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result findAll(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"添加成功！");
    }



}
