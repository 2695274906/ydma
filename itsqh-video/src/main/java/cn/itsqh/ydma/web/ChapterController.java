package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.ChapterService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	/**
	 * 	根据课程id查询所有章节和对应的视频
	 * @param id	课程id
	 * @return	章节和对应视频
	 */
	@GetMapping("/course/chapter/video")
	public YdmaResult findCourseAndChapter(@RequestParam("id")int id,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="8")int size) {
		
		return chapterService.selectByCourseId(id,page,size);
	}
	@GetMapping("/course/chapterId")
	public YdmaResult findChapter(@RequestParam("id")int id) {
		return chapterService.selectByChapterId(id);
	}
}