package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xdl.ydma.RunUserBoot;
import cn.xdl.ydma.util.YdmaConstant;
import cn.xdl.ydma.util.YdmaResult;
import cn.xdl.ydma.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunUserBoot.class})
public class TestUserRegist {

	@Autowired
	private UserController controller;
	
	@Test
	public void test1() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/regist")
			.param("username", "xdl13")
			.param("password", "1234");
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//注册成功在controller 里面是0:注册成功
		//Assert.assertEquals() 1.如果两者一致, 程序继续往下运行. 2. 如果两者不一致, 中断测试方法, 抛出异常信息 
		Assert.assertEquals(YdmaConstant.SUCCESS, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.REGIST_SUCCESS_MSG, ydmaResult.getMsg());
	}
	
	
	@Test
	public void test2() throws Exception {
		//mock对象具有发送HTTP请求，接收响应结果功能
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		//创建一个post请求/user/regist
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/regist")
			.param("username", "paopao2")
			.param("password", "1234111111");
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.ERROR1, ydmaResult.getCode());
		//1:用户名被占用
		Assert.assertEquals(YdmaConstant.REGIST_NAME_ERROR_MSG, ydmaResult.getMsg());
	}
}
