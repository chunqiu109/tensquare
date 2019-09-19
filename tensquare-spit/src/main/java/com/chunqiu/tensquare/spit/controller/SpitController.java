package com.chunqiu.tensquare.spit.controller;

import com.chunqiu.tensquare.entity.PageResult;
import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import com.chunqiu.tensquare.spit.pojo.Spit;
import com.chunqiu.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述
 *
 * @author Wangchunqiu
 * @date $
 */
@RestController
@RequestMapping(value = "/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        //判断用户是否点赞
        String userid = "1234"; //此处暂时写死，之后修改为登录账户

        //从redis中获取用户是否已经点过赞
        if (redisTemplate.opsForValue().get("thumbup_"+ userid + "_" + spitId) != null){
            return new Result(false,StatusCode.REMOTEERROR,"您已经点过赞了！");
        }

        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+ userid + "_" + spitId,"1");
        return new Result(true,StatusCode.OK,"点赞成功！");
    }

    /**
     * 根据父id查询数据
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "根据父id查询数据成功！", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功！", spitService.findAll());
    }

    /**
     * 根据id查询数据
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "根据id查询成功！", spitService.findById(id));
    }

    /**
     * 根据id删除
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功！");
    }

    /**
     * 更新数据
     *
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id, @RequestBody Spit spit) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功！");
    }

    /**
     * 添加数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result findAll(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "添加成功！");
    }


}
