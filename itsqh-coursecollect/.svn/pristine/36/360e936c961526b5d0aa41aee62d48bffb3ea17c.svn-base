package cn.itsqh.ydma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.itsqh.ydma.dao.CollectCourseMapper;
import cn.itsqh.ydma.entity.CollectCourse;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.util.JWTUtil;

@Service
public class CourseCollectServiceImpl implements CourseCollectService {

	@Autowired
	private CollectCourseMapper dao;
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public YdmaResult findCollectCourses(String token) {
		YdmaResult result = new YdmaResult();
		int id = JWTUtil.parseTokenUid(token);
		System.out.println(id);
		List<CollectCourse> collectCourses = dao.selectByUid(id);
		if(collectCourses.size()!=0) {
			for (CollectCourse collectCourse : collectCourses) {
				String url = "http://localhost:7002/course/"+collectCourse.getCourseId();
				System.out.println(collectCourse.getCourseId());
				YdmaResult obj = restTemplate.getForObject(url, YdmaResult.class);
				if(obj.getCode()==YdmaConstant.SUCCESS) {
					Object data = obj.getData();
					collectCourse.setCourses(data);
				}
			}
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
			result.setData(collectCourses);
			System.out.println(collectCourses);
			return result;
		}
		result.setCode(YdmaConstant.ERROR1);
		result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		return result;
	}
	
	
}
