package usermap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.itsqh.ydma.RunUserBoot;
import cn.itsqh.ydma.dao.UserMapper;
import cn.itsqh.ydma.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunUserBoot.class})
public class TestUserMapper {
	
	@Autowired
	private UserMapper userDao;
	
	@Test
	public void test1() {
		User user = userDao.selectByPrimaryKey(1);
		System.out.println(user.getId() + " " + user.getName());
	}
	
	@Test
	public void test2() {
		User user = userDao.selectByName("paopao");
		System.out.println(user.getId() + " " + user.getName());
	}
}
