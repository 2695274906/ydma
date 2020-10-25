package cn.xdl.ydma.service;
import cn.xdl.ydma.entity.Video;
import cn.xdl.ydma.util.YdmaResult;
public interface VideoService {

	//根据用户Id查询user用户
		public YdmaResult<Video> load(int id);
	
}
