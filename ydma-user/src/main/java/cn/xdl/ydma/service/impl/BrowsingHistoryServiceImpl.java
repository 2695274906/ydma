package cn.xdl.ydma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.dao.BrowsingHistoryMapper;
import cn.xdl.ydma.entity.BrowsingHistory;
import cn.xdl.ydma.service.BrowsingHistoryService;

@Service
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {
	
	@Autowired
	private BrowsingHistoryMapper  browsingHistoryMapperDao;
	//1.根据浏览历史记录id查询BrowsingHistory
	@Override
	public BrowsingHistory load(int id) {
		BrowsingHistory browsingHistory = browsingHistoryMapperDao.selectByPrimaryKey(id);
		return browsingHistory;
	}

	

}
