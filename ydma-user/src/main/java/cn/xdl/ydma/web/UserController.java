package cn.xdl.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.service.UserService;
import cn.xdl.ydma.util.YdmaResult;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	//GetMaping中的Id表示路径变量，不是请求参数，所以使用这种方式
	@GetMapping("/user/{id}")
	public YdmaResult<User> load(@PathVariable("id")int id) {
		return userService.load(id);
	}
	
	@PostMapping("/user/regist")
	public YdmaResult regist(String username,String password) {
		
		return  userService.add(username, password);
	}
	@PostMapping("/user/login")
	public YdmaResult login(String username,String password) {
		
		return  userService.check(username, password);
	}
	
	@PostMapping("user/token")
	public YdmaResult token(String token) {
			
		return  userService.token(token);
	}
	
	
}
