package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.LoginHistoryService;

@RestController
public class LoginHistoryController {
	
	@Autowired
	private LoginHistoryService loginHistoryDao;
	
	@GetMapping("/login/addhistory")
	public YdmaResult addLoginHistory(int userId) {
		return loginHistoryDao.addLoginHistory(userId);
	}
	
	@GetMapping("/login/findhistory")
	public YdmaResult findLoginHistory(int userId) {
		return loginHistoryDao.findLoginHistoryByUserId(userId);
	}
}
