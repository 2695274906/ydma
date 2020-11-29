package Test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.plugins.MockMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.itsqh.ydma.RunUserBoot;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.entity.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunUserBoot.class})
public class TestUserController {
	
	@Autowired
	private UserController userController;
	//测试登录功能
	@Test
	public void test1() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
		
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/login")
				.param("name", "test2")
				.param("password", "1234");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(YdmaConstant.SUCCESS,ydmaResult.getCode());
		Assert.assertEquals(YdmaConstant.LOGIN_SUCCESS_MSG,ydmaResult.getMsg());
		
	}
	//测试注册功能
	@Test
	public void test2() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/regist")
				.param("name", "pop")
				.param("password", "123");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.ERROR1);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.REGIST_ERROR1_MSG);
	}
	//测试登录功能
	@Test
	public void test3() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/login")
		.param("name", "pop")
		.param("password", "123");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.SUCCESS);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.LOGIN_SUCCESS_MSG);
	}
	//测试修改密码
	@Test
	public void test4() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/updatapassword")
				.param("name", "sqh")
				.param("oldPassword", "123")
				.param("newPassword1", "123")
				.param("newPassword2", "123");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.ERROR1);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.UPDATE_PASSWORD_ERROR2_MSG);
	}
	//用户信息更新成功
	@Test
	public void test5() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(userController).build();
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/updataAll")
				.param("name", "test")
				.param("nick_name", "昵称")
				.param("position", "职位")
				.param("sex", "男")
				.param("location", "北京")
				.param("signature", "你哥我是个传说，你不是")
				.param("image", "c");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.SUCCESS);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.UPDATE_USER_SUCCESS_MSG);
	}
	
	
}
