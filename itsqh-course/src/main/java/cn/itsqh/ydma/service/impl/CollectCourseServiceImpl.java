package cn.itsqh.ydma.service.impl;

import java.util.List;

import cn.itsqh.ydma.dao.CollectCourseMapper;
import cn.itsqh.ydma.entity.CollectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CollectCourseService;
import cn.itsqh.ydma.util.JWTUtil;

@Service
public class CollectCourseServiceImpl implements CollectCourseService {

	@Autowired
	private CollectCourseMapper collectCourseDao;
	
	@Override
	public YdmaResult addCollectCourse(int userId, int courseId) {

		return null;
	}

	@Override
	public YdmaResult findCollectCourse(String token, int page, int size) {
		//		根据token查询用户id
		YdmaResult result = new YdmaResult();
		int uid = JWTUtil.parseTokenUid(token);
		PageHelper.startPage(page, size);
		List<CollectCourse> list = collectCourseDao.selectCollectCourseByUserId(uid);
		if(list.isEmpty()) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
			return result;
		}else{
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
			result.setData(list);
		}
		
		return result;
	}
	
	
}
