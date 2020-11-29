package dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itsqh.ydma.RunVideoBoot;
import cn.itsqh.ydma.dao.VideoMapper;
import cn.itsqh.ydma.entity.Video;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunVideoBoot.class})
public class TestVoidDao {
	
	@Autowired
	private VideoMapper videoDao;
	
	@Test
	public void test1() {
		
		List<Video> list = videoDao.selectByLike("oracle");
		list.forEach(action -> System.out.println(action.getId() + " " + action.getName()));
	}
}
