package cn.xdl.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cn.xdl.ydma.entity.Course;
import cn.xdl.ydma.service.CourseService;

@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;
	//GetMaping中的Id表示路径变量，不是请求参数，所以使用这种方式
	@GetMapping("/course/{id}")
	public Course load(@PathVariable("id") int id) {
		return courseService.load(id);
	}
	
	
}
