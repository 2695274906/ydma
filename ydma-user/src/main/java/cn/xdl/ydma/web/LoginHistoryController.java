package cn.xdl.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.entity.LoginHistory;
import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.service.LoginHistoryService;
import cn.xdl.ydma.service.UserService;

@RestController
public class LoginHistoryController {
	@Autowired
	private LoginHistoryService loginHistoryService;
	//GetMaping中的Id表示路径变量，不是请求参数，所以使用这种方式
	@GetMapping("/LoginHistory/{id}")
	public LoginHistory load(@PathVariable("id")int id) {
		return loginHistoryService.load(id);
	}
	
}
