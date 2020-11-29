package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.VideoService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@GetMapping("/video/findByVideoId")
	public YdmaResult findByVideoId(int vid) {
		
		return videoService.findVideoById(vid);
	}
	
	
	@GetMapping("/user/findLike")
	public YdmaResult fandLike(
			@RequestParam("name")String name,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="4")int size ) {
		return videoService.selectLike(name, page, size);
	}
}
