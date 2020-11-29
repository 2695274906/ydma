package cn.itsqh.ydma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.itsqh.ydma.dao.DirectionMapper;
import cn.itsqh.ydma.entity.Direction;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.DirectionService;

@Service
public class DirectionServiceImpl implements DirectionService{

	@Autowired
	private DirectionMapper DirectionDao;
	//RestTemplate实现对象的发送和请求
	@Autowired
	private RestTemplate restemplate;
	
	@Override
	public YdmaResult findAll() {
		YdmaResult result = new YdmaResult();
		List<Direction> list = DirectionDao.selectAll();
		if(list != null) {
			for(Direction d:list) {
				String url = "http://localhost:7002/course/direction?did="+d.getId();
				//System.out.println(url);
				YdmaResult courseResult =restemplate.getForObject(url, YdmaResult.class);
				if(courseResult.getCode()==YdmaConstant.SUCCESS) {
					List courseList = (List)courseResult.getData();
					d.setCourses(courseList);
				}
			}
				result.setData(YdmaConstant.SUCCESS);
				result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
				result.setData(list);
		}else {
			result.setData(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		}
		return result;
	}

}
