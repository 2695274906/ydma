package cn.itsqh.ydma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.itsqh.ydma.dao.EvaluateMapper;
import cn.itsqh.ydma.entity.Evaluate;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	private EvaluateMapper evaluateDao;
	
	@Override
	public YdmaResult addEvaluate(int userid,int videoid,String context) {
		YdmaResult result = new YdmaResult();
		//		添加评论
		Evaluate record = new Evaluate();
		record.setContext(context);
		record.setUseId(userid);
		record.setVideoId(videoid);
		record.setLikeCount(0);
		record.setPublishTime(new Date());
		int ia = evaluateDao.insert(record);
		//		添加失败
		if(ia == 0 ) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.INSERT_ERROR1_MSG);
			return result;
		}
		//		添加成功
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.INSERT_SUCCESS_MSG);
		return result;
	}


	@Override
	public YdmaResult findByVideoId(int id,int page,int size) {
		YdmaResult result = new YdmaResult();
		PageHelper.startPage(page, size);
		List<Evaluate> list = evaluateDao.selectByVideoId(id);
		result.setCode(YdmaConstant.SUCCESS );
		result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
		result.setData(list);
		return result;
	}

}
