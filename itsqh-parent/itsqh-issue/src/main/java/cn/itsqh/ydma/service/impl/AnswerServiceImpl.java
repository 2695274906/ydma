package cn.itsqh.ydma.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsqh.ydma.dao.AnswerMapper;
import cn.itsqh.ydma.entity.Answer;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerMapper answerDao;
	
	@Override
	public YdmaResult addAnswer(int userId, int issueId, String context) {
		YdmaResult result = new YdmaResult();
		Answer answer = new Answer();
		answer.setContext(context);
		answer.setPublishTime(new Date());
		answer.setUserId(userId);
		answer.setIssueId(issueId);
		int ia = answerDao.insert(answer);
		if(ia == 0) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.INSERT_ERROR1_MSG);
			return result;
		}
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.INSERT_SUCCESS_MSG);
		return result;
	}

}
