package cn.itsqh.ydma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.itsqh.ydma.dao.IssueMapper;
import cn.itsqh.ydma.entity.Issue;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private IssueMapper issueDao;
	
	@Override
	public YdmaResult addIssue(int userid, int videoid, String context, String headline) {
		YdmaResult result = new YdmaResult();
		Issue issue = new Issue(); 
		issue.setHeadline(headline);
		issue.setUserId(userid);
		issue.setVideoId(videoid);
		issue.setPublishTime(new Date());
		issue.setIssueContext(context);
		int ia = issueDao.insert(issue);
		System.out.println(ia > 0 ? "提问成功":"提问失败");
		if(ia == 0 ) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.INSERT_ERROR1_MSG);
			return result;
		}
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.INSERT_SUCCESS_MSG);
		return result;
	}

	@Override
	public YdmaResult findByVideoId(int videoId,int page, int size) {
		YdmaResult result = new YdmaResult();
		PageHelper.startPage(page, size);
		List<Issue> list = issueDao.selectByVideoId(videoId);
		if(list == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
			return result;
		}
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
		result.setData(list);
		return result;
	}

	@Override
	public YdmaResult updateRigheAnswer(int id,int userId,int rightId) {
		YdmaResult result = new YdmaResult();
		//		判断问题用户id和当前用户是否一致
		Issue issue = issueDao.selectByPrimaryKey(id);
		if(userId != issue.getId()) {
			//		不一致,返回不允许修改
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.UPDATE_ISSUE_ERROR1_MSG);
			return result;
		}
		//		一致则设置答案
		Issue issue2 = new Issue();
		issue2.setRightAnswerId(rightId);
		int ia = issueDao.updateByPrimaryKeySelective(issue2);
		if(ia == 0 ) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.UPDATE_ISSUE_ERROR1_MSG);
			return result;
		}
		
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.UPDATE_ISSUE_SUCCESS_MSG);
		return result;
	}

}
