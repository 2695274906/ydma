package cn.xdl.ydma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xdl.ydma.dao.LoginHistoryMapper;
import cn.xdl.ydma.entity.LoginHistory;
import cn.xdl.ydma.service.LoginHistoryService;


@Service
public class LoginHistoryServiceImpl implements LoginHistoryService{
	@Autowired
	LoginHistoryMapper loginHistoryDao;
	
	@Override
	public LoginHistory load(int id) {
		LoginHistory log1 = loginHistoryDao.selectByPrimaryKey(id);
		return log1;
	}

}
