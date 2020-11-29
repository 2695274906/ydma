package cn.itsqh.ydma.service;

import java.util.Date;

import cn.itsqh.ydma.entity.YdmaResult;

public interface CollectNoteService {

	public YdmaResult insert(int userId,int noteId,Date time);
}
