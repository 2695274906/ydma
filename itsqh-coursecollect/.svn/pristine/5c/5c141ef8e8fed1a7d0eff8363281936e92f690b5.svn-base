package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CourseCollectService;

@RestController
@CrossOrigin(value= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class CourseCollectController {

	@Autowired
	private CourseCollectService  collectService;
	
	@GetMapping("/course/collect")
	public YdmaResult courseCollect(@RequestParam("token")String token) {
		return collectService.findCollectCourses(token);
	}
}
