package cn.itsqh.ydma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageHelper;

import cn.itsqh.ydma.dao.ChapterMapper;
import cn.itsqh.ydma.entity.Chapter;
import cn.itsqh.ydma.entity.Video;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.ChapterService;

@Service
public class ChapterServiceImp implements ChapterService {

	@Autowired
	private ChapterMapper chapterDao;
	
	@Autowired
	private RestTemplate restemplate;
	
	@Override
	//@Cacheable(value="courseid")
	public YdmaResult selectByCourseId(int id,int page, int size) {
		YdmaResult result = new YdmaResult();
		PageHelper.startPage(page, size);
		List<Chapter> list = chapterDao.selectByCourseId(id);
		if(list != null) {
			for (Chapter chapter : list) {
				List<Video> videos = chapter.getVideos();
				//		通过视频id查询相关评论
				for (Video videoId : videos) {
					//		获取到视频id
					int vid = videoId.getId();
					//System.out.println(vid);
					//		通过视频id获取该视频的所有的笔记
					String url1 = "http://localhost:7005/user/findByVideoId?id="+vid;
					YdmaResult courseResult1 =restemplate.getForObject(url1, YdmaResult.class);
					if(courseResult1.getCode()==YdmaConstant.SUCCESS) {
						List courseList = (List)courseResult1.getData();
						chapter.setNotes(courseList);
					}
					//		根据视频id获取该视频的所有的评论
					String url2 = "http://localhost:7007/user/findevaluate?id="+vid;
					YdmaResult courseResult2 =restemplate.getForObject(url1, YdmaResult.class);
					if(courseResult1.getCode()==YdmaConstant.SUCCESS) {
						List courseList = (List)courseResult1.getData();
						chapter.setEvaluates(courseList);
					}
				}
			}
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
			result.setData(list);
			return result;
		}
		result.setCode(YdmaConstant.ERROR1);
		result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		return result;
	}

	@Override
	public YdmaResult selectByChapterId(int id) {
		YdmaResult result = new YdmaResult();
		Chapter chapter = chapterDao.selectByPrimaryKey(id);
		if(chapter!=null) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
			result.setData(chapter);
		}else {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		}
		return result;
	}

}
