package dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itsqh.ydma.RunDirectionBoot;
import cn.itsqh.ydma.dao.DirectionMapper;
import cn.itsqh.ydma.entity.Direction;
import cn.itsqh.ydma.entity.Subject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunDirectionBoot.class})
public class TestDirectionMapper {
	
	@Autowired
	private DirectionMapper directionDao;
	
	@Test
	public void test1() {
		List<Direction> list = directionDao.selectAll();
		list.forEach(directions -> 
		{
			System.out.println(directions.getId() + " " + directions.getName());
			List<Subject> subject =  directions.getSubjects();
			subject.forEach(action -> System.out.println(action.getName())); 
			
		});
	}
}
