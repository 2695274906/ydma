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
import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.util.YdmaConstant;
import cn.xdl.ydma.util.YdmaResult;
import cn.xdl.ydma.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunUserBoot.class})
public class TestSpringMVC {
	//将controller注入进来
	@Autowired
	private UserController controller;
	//springMVC进行流程测试
	@Test
	public void test1() {
		YdmaResult<User> result = controller.load(1);
		System.out.println(result.getCode()+" "+result.getMsg());
	}
	@Test
	public void test2() throws Exception {
		//mock对象具有发送HTTP请求，接收响应结果功能
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		//创建一个get请求/user/1
		RequestBuilder request = MockMvcRequestBuilders.get("/user/1");
		//发送执行请求,获取返回结果
		MvcResult result = mock.perform(request).andReturn();
		//content表示显示返回结果信息  将返回结果信息转换成字符串：getContentAsString
		String content = result.getResponse().getContentAsString();
		System.out.println("转成字符串的结果是"+content);
		//断言 assertion是一种经典的调试、测试方式。
		ObjectMapper mapper = new ObjectMapper();
		//YdmaResult是返回的结果信息 然后调用getCode()方法得到一个数字
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.SUCCESS, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOAD_SUCCESS_MSG, ydmaResult.getMsg());
	}
	
	@Test
	public void test3() throws Exception {
		//mock对象具有发送HTTP请求，接收响应结果功能
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		//创建一个get请求/user/1
		RequestBuilder request = MockMvcRequestBuilders.get("/user/2");
		//发送执行请求,获取返回结果
		MvcResult result = mock.perform(request).andReturn();
		//显示返回结果信息
		String content = result.getResponse().getContentAsString();
//		System.out.println(content);
		//断言
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		Assert.assertEquals(YdmaConstant.ERROR1, ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOAD_ERROR_MSG, ydmaResult.getMsg());
	}
	
}
