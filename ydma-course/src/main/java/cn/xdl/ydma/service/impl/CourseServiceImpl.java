package cn.xdl.ydma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.dao.CourseMapper;
import cn.xdl.ydma.entity.Course;
import cn.xdl.ydma.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseMapper  courseMapperDao;
	
	@Override
	public Course load(int id) {
		Course cou1 = courseMapperDao.selectByPrimaryKey(id);
		return cou1;
	}

}
