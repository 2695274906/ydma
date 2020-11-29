package cn.itsqh.ydma.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.javap.TypeAnnotationWriter.Note;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CollectNoteService;

@RestController
public class CollectNoteController {
	
	@Autowired
	private CollectNoteService collectNoteDao;
	
	@GetMapping("")
	public YdmaResult addCollectNote(int userId,int noteId, Date time) {
		return collectNoteDao.insert(userId, noteId, time);
	}
}
