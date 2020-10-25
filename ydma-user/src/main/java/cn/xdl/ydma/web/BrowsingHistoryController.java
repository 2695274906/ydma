package cn.xdl.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.xdl.ydma.entity.BrowsingHistory;
import cn.xdl.ydma.service.BrowsingHistoryService;
@RestController
public class BrowsingHistoryController {
	@Autowired
	private BrowsingHistoryService browsingHistoryService;
	//GetMaping中的Id表示路径变量，不是请求参数，所以使用路径变量
	@GetMapping("/BrowsingHistory/{id}")
	public BrowsingHistory load(@PathVariable("id")int id) {
		return  browsingHistoryService.load(id);
	}
}
