package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.BrowsingHistoryService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class BrowsingHistoryController {

	@Autowired
	private BrowsingHistoryService browsingHisDao;
	
	/**
	 * 	根据用户id查询浏览记录
	 * @param userId
	 * @return
	 */
	@PostMapping("/brohis/findByUserId")
	public YdmaResult findBroHisByUserId(String token) {
		if(token.length()!=0) {
			return browsingHisDao.findBroHisByUserId(token);
		}
		YdmaResult result = new YdmaResult();
		result.setCode(YdmaConstant.ERROR1);
		result.setMsg("Token不存在或已过期,请重新登录");
		return  result;
	}
	
//	@PostMapping("/brohis/findByToken")
//	public YdmaResult findByToken(String token) {
//		System.out.println("====================");
//		return browsingHisDao.findByToken(token);
//	}
}
