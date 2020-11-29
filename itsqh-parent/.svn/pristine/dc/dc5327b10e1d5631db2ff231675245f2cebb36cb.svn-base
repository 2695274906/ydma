package dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.itsqh.ydma.RunNoteBoot;
import cn.itsqh.ydma.dao.NoteMapper;
import cn.itsqh.ydma.entity.Note;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= { RunNoteBoot.class})
public class TestNoteDao {
	
	@Autowired
	private NoteMapper noteDao;
	
	@Test
	public void test1() {
		Note note = new Note();
		note.setContext("测试添加内容");
		note.setTitle("测试添加标题");
		note.setUserId(30);
		note.setVideoId(11);
		note.setPublishTime(new Date());
		System.out.println("123");
		int ia = noteDao.insertSelective(note);
		System.out.println(ia>0?"添加笔记成功":"添加笔记失败");
	}
	
	@Test
	public void test2() {
		List<Note> list = noteDao.selectByVideoId(1);
		list.forEach(action -> System.out.println(action.getId() + " " + action.getContext()));
	}
	
	@Test
	public void test3() {
		List<Note> list = noteDao.selectByUserIdAndVideoId(25,28);
		list.forEach(action -> System.out.println(action.getId() + " " + action.getContext()));
	}
}
