package cn.xdl.ydma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xdl.ydma.dao.VideoMapper;
import cn.xdl.ydma.entity.Video;
import cn.xdl.ydma.util.YdmaConstant;
import cn.xdl.ydma.util.YdmaResult;

@Service
public class VideoServiceImp implements VideoService {

	@Autowired
	private VideoMapper videoDao;
	@Override
	public YdmaResult<Video> load(int id) {
				//如果没有这个方法，则需要自己写方法
				YdmaResult<Video> result = new YdmaResult<Video>();
				//通过用户id查询用视频信息
				Video video = videoDao.findVideoById(id);
				//如果video为空  error4:"未找到查询记录";
				if(video==null) {
					result.setCode(YdmaConstant.ERROR4);
					result.setMsg(YdmaConstant.LOAD_ERROR_MSG);
					return result;
				}
				// 0:查询成功
				result.setCode(YdmaConstant.SUCCESS);
				result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
				result.setData(video);
		return result;
	}

}
