package login.test;

import java.io.UnsupportedEncodingException;

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

import cn.itsqh.ydma.RunLoginHistoryBoot;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.LoginHistoryService;
import cn.itsqh.ydma.web.LoginHistoryController;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunLoginHistoryBoot.class})
public class TestLoginHotory {

	
	@Autowired
	private LoginHistoryController logDao;
	
	@Test
	public void test1() throws Exception {
MockMvc mock = MockMvcBuilders.standaloneSetup(logDao).build();
		
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/login/findhistory")
				.param("userId", "38");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.SUCCESS);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.SELECT_SUCCESS_MSG);
		
	}
}
