package cn.xdl.ydma.service;

import cn.xdl.ydma.entity.Course;

public interface CourseService {
	//根据用户Id查询课程信息
		public Course load(int id);
}
