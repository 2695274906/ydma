package cn.itsqh.ydma.service;

import cn.itsqh.ydma.entity.YdmaResult;

public interface IssueService {
	
	/**
	 * 	提出问题
	 * @param userid	用户id 
	 * @param videoid	视频id
	 * @param context	问题内容
	 * @param headline	标题
	 * @return
	 */
	public YdmaResult addIssue(int userid,int videoid,String context,String headline);
	
	/**
	 * 	根据视频id 查询提问
	 * @param videoId
	 * @return
	 */
	public YdmaResult findByVideoId(int videoId,int page, int size);
	
	/**
	 * 	设置正确答案
	 * @param id 问题id
	 * @param rightId 正确答案id
	 * @return
	 */
	public YdmaResult updateRigheAnswer(int id,int userId,int rightId);
}
