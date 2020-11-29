package cn.itsqh.ydma.service;

import cn.itsqh.ydma.entity.YdmaResult;

public interface EvaluateService {
	
	/**
	 * 	添加评论
	 * @param evaluate 
	 * @return
	 */
	public YdmaResult addEvaluate(int userid,int videoid,String context);
	
	/**
	 * 	根据视频id查询评论
	 * @param id	视频id
	 * @return
	 */
	public YdmaResult findByVideoId(int id,int page,int size);
}
