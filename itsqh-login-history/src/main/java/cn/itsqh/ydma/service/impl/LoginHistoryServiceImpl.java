package cn.itsqh.ydma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsqh.ydma.dao.LoginHistoryMapper;
import cn.itsqh.ydma.entity.LoginHistory;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.LoginHistoryService;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	@Autowired
	private LoginHistoryMapper loginhistoryDao;
	
	@Override
	public YdmaResult addLoginHistory(int userId) {
		YdmaResult result = new YdmaResult();
		LoginHistory loghis = new LoginHistory();
		loghis.setLoginTime(new Date());
		//		添加登录记录,暂时将地址写死
		loghis.setCity("北京-昌平");
		//		ip写死
		loghis.setIp("0:0:0:0:0:0:0:1");
		loghis.setUserId(userId);
		int ia = loginhistoryDao.insertSelective(loghis);
		if(ia != 0) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setData(loghis);
			result.setMsg(YdmaConstant.INSERT_LOGINHISTORY_SUCCESS_MSG);
		}else {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.INSERT_LOGINHISTORY_ERROR_MSG);
		}
		return result;
	}

	@Override
	public YdmaResult findLoginHistoryByUserId(int userId) {
		YdmaResult result = new YdmaResult();
		List<LoginHistory> list = loginhistoryDao.selectByUserId(userId);
		if(list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		}
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
		result.setData(list);
		return result;
	}
	
}
