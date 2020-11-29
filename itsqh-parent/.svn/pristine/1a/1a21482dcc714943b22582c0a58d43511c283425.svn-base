package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.Note;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.NoteService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	/**
	 * 	添加笔记
	 * @param note 笔记对象
	 * @return	
	 */
	@PostMapping("/user/addNote")
	public YdmaResult addNote(int userId,int videoId,String context) {
		return noteService.insert(userId, videoId, context);
	}
	
	
	/**
	 * 	根据视频id查询笔记
	 * @param id	视频id
	 * @return	
	 */
	@GetMapping("/user/findByVideoId")
	public YdmaResult findByVideoId(@RequestParam("id")int id,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size) {
		
		return noteService.selectByVideoId(id,page,size);
	}
	
	/**
	 * 	查看自己的笔记
	 * @param userId	用户id
	 * @param voidId	视频id
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/user/findByUserIdAndVoideId")
	public YdmaResult findByUserId(
			@RequestParam("userId")int userId,
			@RequestParam("voidId")int voidId,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="5")int size) {
		return noteService.selectByUserIdAndVideoId(userId,voidId,page,size);
	}
}
