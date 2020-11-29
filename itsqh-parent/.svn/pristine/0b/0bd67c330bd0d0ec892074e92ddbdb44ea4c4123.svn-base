package cn.itsqh.ydma.service;

import cn.itsqh.ydma.entity.Note;
import cn.itsqh.ydma.entity.YdmaResult;

public interface NoteService {
	/**
	 * 	添加笔记	
	 * @param note	笔记对象
	 * @return
	 */
	public YdmaResult insert(int userId,int videoId,String context);
	
	/**
	 * 	通过视频id获取该视频的所有的笔记
	 * @param id	视频id
	 * @return
	 */
	public YdmaResult selectByVideoId(int id,int page, int size);
	
	/**
	 * 	通过用户id和视频id查询笔记
	 * @param userId	用户id
	 * @param voideId 	视频id
	 * @return	
	 */
	public YdmaResult selectByUserIdAndVideoId(int userId,int voidId,int page, int size);
	
	/**
	 * 	根据笔记id删除笔记
	 * @param id	笔记id
	 * @return
	 */
	public YdmaResult deleteByPrimaryKey(int id);
}
