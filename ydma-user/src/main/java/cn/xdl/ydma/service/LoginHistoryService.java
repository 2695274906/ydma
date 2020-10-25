package cn.xdl.ydma.service;

import cn.xdl.ydma.entity.LoginHistory;

public interface LoginHistoryService {
	//根据登录Id查询登录信息
	public LoginHistory load(int id);
	
}
