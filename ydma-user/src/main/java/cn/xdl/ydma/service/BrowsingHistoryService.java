package cn.xdl.ydma.service;

import cn.xdl.ydma.entity.BrowsingHistory;

public interface BrowsingHistoryService {
	//根据浏览历史id查询历史记录
		public BrowsingHistory load(int id);
}
