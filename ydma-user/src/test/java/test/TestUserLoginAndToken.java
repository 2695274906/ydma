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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xdl.ydma.RunUserBoot;
import cn.xdl.ydma.util.YdmaConstant;
import cn.xdl.ydma.util.YdmaResult;
import cn.xdl.ydma.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunUserBoot.class})
public class TestUserLoginAndToken {

	@Autowired
	private UserController controller;
	
	@Test
	public void test1() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		//构建请求对象RequestBuilder 通过输入url（/user/login）对controller进行测试
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/login")
			.param("username", "paopao")
			.param("password", "123");
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.ERROR2, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOGIN_SUCCESS_MSG, ydmaResult.getMsg());
	}
	
	@Test
	public void test2() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/login")
			.param("username", "xdl2")
			.param("password", "123");
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//2：登录密码错误
		Assert.assertEquals(YdmaConstant.ERROR2, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOGIN_PASSWORD_ERROR_MSG, ydmaResult.getMsg());
	}
	//登录和token验证
	@Test
	public void test3() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/login")
			.param("username", "xdl2")
			.param("password", "1234");
		//执行请求得到的结果
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		System.out.println("打印的第1个结果是"+ydmaResult.getData());
		
		MockMvc mock2 = MockMvcBuilders.standaloneSetup(controller).build();
		request = MockMvcRequestBuilders.post("/user/token")
					.param("token",(String)ydmaResult.getData());
		result = mock.perform(request).andReturn();
		content = result.getResponse().getContentAsString();
		mapper = new ObjectMapper();
		ydmaResult = mapper.readValue(content, YdmaResult.class);
		System.out.println("打印的第2个结果："+ydmaResult.getData());
		System.out.println("得到的getCode()"+ydmaResult.getCode());
		//1：登录成功
		Assert.assertEquals(YdmaConstant.SUCCESS, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.TOKEN_SUCCESS5_MSG, ydmaResult.getMsg());
	}
	@Test
	public void test4() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		RequestBuilder request = 
			MockMvcRequestBuilders.post("/user/login")
			.param("username", "xdl");
		MvcResult result = mock.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//期待的效果是：9：请求格式参数错误
		Assert.assertEquals(YdmaConstant.ERROR9, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.PARAMS_ERROR_MSG, ydmaResult.getMsg());
	}
	
}
