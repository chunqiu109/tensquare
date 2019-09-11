package com.chunqiu.tensquare.user.controller;

import com.chunqiu.tensquare.base.pojo.Label;
import com.chunqiu.tensquare.base.service.LabelService;
import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *功能描述 
 * @author Wangchunqiu
 */
@RestController
@RequestMapping("/label")
public class UserController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询所有标签
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功！",labelService.findAll());
    }

    /**
     * 根据id查询标签
     * @return
     */
    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        return new Result(true, StatusCode.OK,"根据id查询成功！",labelService.findById(id));
    }

    /**
     * 删除标签
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
        labelService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功！");
    }

    /**
     * 修改标签
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable String id){
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功！");
    }

    /**
     * 添加标签
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"添加成功！");
    }

}
