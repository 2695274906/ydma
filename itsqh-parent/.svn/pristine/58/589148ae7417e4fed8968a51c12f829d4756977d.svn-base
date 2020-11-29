package cn.itsqh.ydma.service;

import cn.itsqh.ydma.entity.YdmaResult;

public interface CourseService {
	
	/**
	 * 	根据课程id查询课程
	 * @param id
	 * @return
	 */
	public YdmaResult loadCourse(int id); 
	
	/**
	 * 	根据学科id查询课程
	 * @param sid	学科id
	 * @param page
	 * @param size
	 * @return
	 */
	public YdmaResult loadSubjectCourse(int sid, int page, int size);
	
	/**
	 * 	根据类别id查询课程
	 * @param did 
	 * @param page
	 * @param size
	 * @return
	 */
	public YdmaResult loadDirectionCourses(int did, int page,int size);
	
	/**
	 * 	根据课程价格查询课程
	 * @param price 课程价格0
	 * @param page
	 * @param size
	 * @return
	 */
	public YdmaResult loadPrice(int page,int size);
	
	/**
	 * 	根据学习人数降序排序分页查询课程
	 * @param page
	 * @param size
	 * @return
	 */
	public YdmaResult loadLearnCount(int page,int size);
	
	/**
	 * 	根据学科id查询学科降序排序
	 * @param subjectId	学科id
	 * @param page
	 * @param size
	 * @return
	 */
	public YdmaResult loadScore(int subjectId,int page,int size);
}
