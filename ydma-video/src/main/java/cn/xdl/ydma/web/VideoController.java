package cn.xdl.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cn.xdl.ydma.entity.Video;
import cn.xdl.ydma.service.VideoService;
import cn.xdl.ydma.util.YdmaResult;

@RestController
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	//GetMaping中的Id表示路径变量，不是请求参数，所以使用这种方式
	@GetMapping("/video/{id}")
	public YdmaResult<Video> load(@PathVariable("id")int id) {
		return videoService.load(id);
	}
	
	
	
}
