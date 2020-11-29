package cn.itsqh.ydma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.itsqh.ydma.dao.BrowsingHistoryMapper;
import cn.itsqh.ydma.entity.BrowsingHistory;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.BrowsingHistoryService;
import cn.itsqh.ydma.util.JWTUtil;

@Service
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {

	@Autowired
	BrowsingHistoryMapper browHisDao;

	@Autowired
	private RestTemplate restemplate;
	
	@Override
	public YdmaResult findBroHisByUserId(String token) {
		YdmaResult result = new YdmaResult();
		//从token中提取UID
		int userId = JWTUtil.parseTokenUid(token);
		
		//查询用户对应记录
	 	List<BrowsingHistory> list =  browHisDao.selectByUserId(userId);
	 	
	 	if(list != null) {
	 		for (BrowsingHistory browsingHistory : list) {
	 			//	同过视频id  获取视频信息
				String url = "http://localhost:7003/video/findByVideoId?vid=" +browsingHistory.getVideoId();
				YdmaResult courseResult =restemplate.getForObject(url, YdmaResult.class);
				if(courseResult.getCode()==YdmaConstant.SUCCESS) {
					Object video = (Object)courseResult.getData();
					//System.out.println("video="+video);
					browsingHistory.setVideo(video);
				}
				//获取course 内容
				String url2 = "http://localhost:7002/course/"+browsingHistory.getCourseId();
				YdmaResult courseResult2 =restemplate.getForObject(url2, YdmaResult.class);
				if(courseResult2.getCode()==YdmaConstant.SUCCESS) {
					Object course = (Object)courseResult2.getData();
					browsingHistory.setCourse(course);
				}
				//获取note 数量和内容
				String url3 = "http://localhost:7005/user/findByUserIdAndVoideId2?userId="+browsingHistory.getUseId()+"&voidId="+browsingHistory.getVideoId();
				YdmaResult courseResult3 =restemplate.getForObject(url3, YdmaResult.class);
				if(courseResult3.getCode()==YdmaConstant.SUCCESS) {
					List note = (List)courseResult3.getData();
					browsingHistory.setNote(note);
				}
			}
	 		result.setCode(YdmaConstant.SUCCESS);
	 		result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
	 		result.setData(list);
	 		
	 	}else {
	 		result.setCode(YdmaConstant.ERROR1);
	 		result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
	 	}
		return result;
	}

	

}
