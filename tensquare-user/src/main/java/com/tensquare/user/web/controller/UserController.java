package com.tensquare.user.web.controller;
import java.util.Map;
import com.chunqiu.tensquare.entity.PageResult;
import com.chunqiu.tensquare.entity.Result;
import com.chunqiu.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.tensquare.user.po.User;
import com.tensquare.user.service.UserService;
/**
 * 控制器层
 * @author BoBoLaoShi
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/**
	 * 增加
	 * @param user
	 */
	@PostMapping
	public Result add(@RequestBody User user  ){
		userService.saveUser(user);
		return new Result(true, StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@PutMapping("/{id}")
	public Result edit(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.updateUser(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public Result remove(@PathVariable String id ){
		userService.deleteUserById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@GetMapping
	public Result list(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findUserList());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@GetMapping("/{id}")
	public Result listById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findUserById(id));
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result list( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findUserList(searchMap));
    }

	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@PostMapping("/search/{page}/{size}")
	public Result listPage(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageResponse = userService.findUserListPage(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<>(pageResponse.getTotalElements(), pageResponse.getContent()) );
	}

	/**
	 * 发送短信验证码
	 * @param mobile
	 * @return
	 */
	@PostMapping("/sendsms/{mobile}")
	public Result sendSmsCheckcode(@PathVariable String mobile){
		userService.saveSmsCheckcode(mobile);
		return  new Result(true,StatusCode.OK,"发送成功");
	}
	/**
	 * 用户注册
	 * @param user
	 */
	@PostMapping("/register/{checkcode}")
	public Result register(@RequestBody User user ,@PathVariable String checkcode ){
		userService.saveUser(user,checkcode);
		return new Result(true,StatusCode.OK,"用户注册成功");
	}
}
