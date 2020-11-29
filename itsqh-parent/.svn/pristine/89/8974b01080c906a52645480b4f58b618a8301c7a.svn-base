package dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.itsqh.ydma.RunNoteCollect;
import cn.itsqh.ydma.dao.CollectNoteMapper;
import cn.itsqh.ydma.entity.CollectNote;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= { RunNoteCollect.class})
public class TestRunNoteCollect {
	
	@Autowired
	private CollectNoteMapper collectNoteDao;
	
	@Test
	public void addCollectNote() {
		CollectNote record = new CollectNote();
		record.setUserId(30);
		record.setNoteId(1);
		record.setCollectTime(new Date());
		int ia = collectNoteDao.insert(record);
		System.out.println(ia>0?"添加成功":"添加失败");
	}
}
