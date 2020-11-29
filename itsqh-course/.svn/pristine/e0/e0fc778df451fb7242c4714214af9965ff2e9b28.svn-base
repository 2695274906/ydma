package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.impl.CollectCourseServiceImpl;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class CollectCourseController {
	
	@Autowired
	private CollectCourseServiceImpl collectCourseDao;
	
	@PostMapping("/collect/findcourse")
	public YdmaResult loadCollectCourse(
			@RequestParam("token")String token,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size
			) {
		return collectCourseDao.findCollectCourse(token, page, size);
	}
}
