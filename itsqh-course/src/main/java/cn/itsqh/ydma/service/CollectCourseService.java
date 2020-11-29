package cn.itsqh.ydma.service;

import cn.itsqh.ydma.entity.YdmaResult;

public interface CollectCourseService {
	
	/**
	 * 	收藏课程
	 * @param userId 用户id
	 * @param courseId	课程id
	 * @return
	 */
	public YdmaResult addCollectCourse(int userId, int courseId);
	
	/**
	 * 	根据token查询收藏课程
	 * @param token 
	 * @return
	 */
	public YdmaResult findCollectCourse(String token, int page, int size);
}
