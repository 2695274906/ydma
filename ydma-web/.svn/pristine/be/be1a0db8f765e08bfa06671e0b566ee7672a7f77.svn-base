package cn.itsqh.ydma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

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
}
