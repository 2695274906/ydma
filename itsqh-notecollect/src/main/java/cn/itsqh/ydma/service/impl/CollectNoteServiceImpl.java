package cn.itsqh.ydma.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import cn.itsqh.util.JWTUtil;
import cn.itsqh.ydma.dao.CollectNoteMapper;
import cn.itsqh.ydma.entity.CollectNote;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.CollectNoteService;

@Service
public class CollectNoteServiceImpl implements CollectNoteService {

	@Autowired
	private CollectNoteMapper collectNoteDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
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

	@Override
	public YdmaResult findByUserId(String token) {
		YdmaResult result = new YdmaResult();
		int uid = JWTUtil.parseTokenUid(token);
		List<CollectNote> collectNote = collectNoteDao.selectByUserId(uid);
		if(collectNote.size()==0) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
			return result;
		}
		for(CollectNote notes:collectNote) {
			String url = "http://localhost:7005/user/findNotes?noteId="+notes.getNoteId();
			YdmaResult noteInfo = restTemplate.getForObject(url, YdmaResult.class);
			Object data = (Object)noteInfo.getData();
			notes.setNotes(data);
		}
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
		result.setData(collectNote);
		return result;
	}

}
