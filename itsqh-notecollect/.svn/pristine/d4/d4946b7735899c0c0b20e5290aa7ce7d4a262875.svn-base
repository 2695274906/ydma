package cn.itsqh.ydma.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.javap.TypeAnnotationWriter.Note;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CollectNoteService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class CollectNoteController {
	
	@Autowired
	private CollectNoteService collectNoteDao;
	//笔记添加
	@GetMapping("")
	public YdmaResult addCollectNote(int userId,int noteId, Date time) {
		return collectNoteDao.insert(userId, noteId, time);
	}
	// 通过用户ID查询收藏的笔记
	@GetMapping("/find/notcollect")
	public YdmaResult findCollectNoteByUserId(String token) {
		return collectNoteDao.findByUserId(token);
	}
}
