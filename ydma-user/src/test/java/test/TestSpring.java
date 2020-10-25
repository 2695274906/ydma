package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import cn.xdl.ydma.RunUserBoot;
import cn.xdl.ydma.dao.UserMapper;
import cn.xdl.ydma.entity.User;

@RunWith(SpringRunner.class)//将SpringRunner指定为junit启动器，可以实例化Spring容器，将容器对象给测试类注入
//@RunWith(SpringJUnit4ClassRunner.class)//低版本
//@ContextConfiguration(locations="classpath:applicationContext.xml")//指定原始的spring框架XML配置文件
@SpringBootTest(classes= {RunUserBoot.class})//指定SpringBoot的启动类
public class TestSpring {
	
	@Autowired
	private UserMapper userDao;
	
	@Test
	public void test1() {
//		ApplicationContext ctx = SpringApplication.run(RunUserBoot.class);
//		UserMapper userDao = ctx.getBean(UserMapper.class);
		User user = userDao.selectByPrimaryKey(1);
		System.out.println("user对象是："+user);
		System.out.println(user.getName()+" "+user.getPassword());
	}
	
	@Test
	public void test2() {
		ApplicationContext ctx = SpringApplication.run(RunUserBoot.class);
		UserMapper userDao = ctx.getBean(UserMapper.class);
		User user = userDao.selectByName("paopao");
		if(user==null) {
			System.out.println("用户名可用");
		}else {
			System.out.println("用户名被占");
		}
	}

}
