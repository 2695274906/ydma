package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.xdl.ydma.RunVideoBoot;
import cn.xdl.ydma.dao.VideoMapper;
import cn.xdl.ydma.entity.Video;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunVideoBoot.class})
public class TestSpring {
	@Autowired
	private VideoMapper videoDao;
	@Test
	public void test1(){
		Video video = videoDao.findVideoById(11);
		if(video==null) {
			System.out.println("未查询到相关视频");
		}
		else {
			System.out.println(video.getName()+" "+video.getUrl());
		}
		
	}
	
	
		
		
		
	
}
