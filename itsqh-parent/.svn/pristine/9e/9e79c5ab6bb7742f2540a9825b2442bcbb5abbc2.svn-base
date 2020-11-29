package cn.itsqh.ydma.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsqh.ydma.dao.CollectNoteMapper;
import cn.itsqh.ydma.entity.CollectNote;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CollectNoteService;

@Service
public class CollectNoteServiceImpl implements CollectNoteService {

	@Autowired
	private CollectNoteMapper collectNoteDao;
	
	@Override
	public YdmaResult insert(int userId,int noteId,Date time) {
		YdmaResult result = new YdmaResult();
		CollectNote note = new CollectNote();
		note.setCollectTime(time);
		note.setNoteId(noteId);
		note.setUserId(userId);
		int ia = collectNoteDao.insert(note);
		System.out.println(ia>0?"笔记收藏成功":"笔记收藏失败");
		if(ia > 0) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.INSERT_NOTE_SUCCESS_MSG);
			return result;
		}
		result.setCode(YdmaConstant.ERROR1);
		result.setMsg(YdmaConstant.INSERT_ERROR1_MSG);
		return result;
		
	}

}
