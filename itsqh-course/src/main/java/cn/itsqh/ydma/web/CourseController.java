package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CourseService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class CourseController {
		
	@Autowired
	private CourseService courseService;
	
	/**
	 * 	根据课程id查询课程
	 * @param id	课程id
	 * @return
	 */
	@GetMapping("/course/{id}")
	public YdmaResult loadCourse(@PathVariable("id")int id) {
		
		return courseService.loadCourse(id);
	}
	
	
	/**
	 * 	根据类别id 查询课程
	 * @param did	类别id
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/course/direction")
	public YdmaResult loadDirectionCourses(
			@RequestParam("did")int did,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size
			) {
		return courseService.loadDirectionCourses(did, page, size);
				
	}
	@GetMapping("/course/directionInfo")
	public YdmaResult loadDirectionCoursesInfo(
			@RequestParam("did")int did,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="100")int size
			) {
		return courseService.loadDirectionCourses(did, page, size);
				
	}
	
	@GetMapping("/course/subjectInfo")
	public YdmaResult loadSubjectCoursesInfo(
			@RequestParam("sid")int sid,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="100")int size
			) {
		return courseService.loadSubjectCourse(sid, page, size);
				
	}
	
	
	/**
	 * 	根据学科id 查询课程
	 * @param sid	学科id
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/course/subject")
	public YdmaResult loadSubjectCourse(
			@RequestParam("sid")int sid,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="4")int size
			) {
		return courseService.loadSubjectCourse(sid, page, size);
				
	}
	
	/**
	 * 	根据课程价格查询课程
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/course/price")
	public YdmaResult loadPrice(
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size) {
		return courseService.loadPrice(page, size);
	}
	
	/**
	 * 	根据学习人数降序排序分页查询课程
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/course/learncount")
	public YdmaResult loadLearnCount(
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size) {
		return courseService.loadLearnCount(page, size);
	}
	
	/**
	 * 	根据学科id查询学科降序排
	 * @param subjectId	学科id
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/course/scoredesc")
	public YdmaResult loadScoreCourse(
			@RequestParam("subjectId")int subjectId,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="6")int size
			) {
		return courseService.loadScore(subjectId, page, size);
				
	}  
	
	@GetMapping("/course/like")
	public YdmaResult loadLikeCourse(
			@RequestParam("name")String name,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="100")int size
			) {
		return courseService.loadLikeCourse(name, page, size);
				
	}
}	
