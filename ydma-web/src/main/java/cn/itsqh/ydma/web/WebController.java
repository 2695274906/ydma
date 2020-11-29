package cn.itsqh.ydma.web;


import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	
	@RequestMapping("/course_list")
	public ModelAndView courseList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_list");
		return mav;
	}
	
	@RequestMapping("/user/self_note")
	public ModelAndView selfNote() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("self_note");
		return mav;
	}
	
	
	@RequestMapping("/user/questions")
	public ModelAndView questions() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("questions");
		return mav;
	}
	@RequestMapping("/video/findByVideoId")
	public ModelAndView video() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("video");
		return mav;
	}
	
	@RequestMapping("/course/subject")
	public ModelAndView sCourses() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_list");
		return mav;
	}
	

	
	@RequestMapping("/course/directionInfo")
	public ModelAndView sCoursesInfo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_list");
		return mav;
	}
	
	@RequestMapping("/course/subjectInfo")
	public ModelAndView sSubjectCoursesInfo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_list");
		return mav;
	}
	
	@RequestMapping("/course/direction")
	public ModelAndView dCourses() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_list");
		return mav;
	}
	

	@RequestMapping("/user/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/user/regist")
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("regist");
		return mav;
	}
	
	
	@RequestMapping("/user/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@GetMapping("/user/search")
	public ModelAndView search() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("search");
		return mav;
	}
	
	@GetMapping("/user/course")
	public ModelAndView course() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course");
		return mav;
	}
	@GetMapping("/course/{id}")
	public ModelAndView courseID() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course");
		return mav;
	}
	
	@GetMapping("/exam/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@GetMapping("/course/course_center")
	public ModelAndView courseCenter() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course_center");
		return mav;
	}
	
	@GetMapping("/course/video")
	public ModelAndView videos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("video");
		return mav;
	}
	@GetMapping("/user/user_center")
	public ModelAndView userCenter() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user_center");
		return mav;
	}
	
	
	@GetMapping("/user/userChangepwd")
	public ModelAndView userChangepwd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user_changepwd");
		return mav;
	}
	
	
	@GetMapping("/user/self_info_edit")
	public ModelAndView selfInfoEdit() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("self_info_edit");
		return mav;
	}
	@GetMapping("/user/user_loginrecord")
	public ModelAndView userLoginrecord() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user_loginrecord");
		return mav;
	}
	
}
