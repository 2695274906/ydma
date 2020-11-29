package dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itsqh.ydma.RunVideoBoot;
import cn.itsqh.ydma.dao.ChapterMapper;
import cn.itsqh.ydma.entity.Chapter;
import cn.itsqh.ydma.entity.Video;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunVideoBoot.class})
public class TestChapterDao {
		
	@Autowired
	private ChapterMapper chapterDao;
	
	@Test
	public void test1() {
		Chapter chapter = chapterDao.selectByPrimaryKey(1);
		System.out.println(chapter.getId() + " " + chapter.getName());
	}
	
	
	@Test
	public void test2() {
		List<Chapter> list = chapterDao.selectByCourseId(2);
		list.forEach(chapter ->
		{
			System.out.println(chapter.getId() + " " + chapter.getName());
			List<Video> videos = chapter.getVideos();
			videos.forEach(video -> System.out.println(video.getId() + " "+ video.getName() + " " +video.getUrl()));
		});
	}
}
