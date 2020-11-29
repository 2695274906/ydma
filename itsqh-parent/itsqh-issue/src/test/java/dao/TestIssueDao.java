package dao;

import java.util.Date;

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

import cn.itsqh.ydma.RunIssueBoot;
import cn.itsqh.ydma.dao.IssueMapper;
import cn.itsqh.ydma.entity.Issue;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.web.AnswerController;
import cn.itsqh.ydma.web.IssueController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunIssueBoot.class})
public class TestIssueDao {

	@Autowired
	private IssueMapper issueDao;
	
	@Autowired
	private IssueController issueController;
	@Autowired
	private AnswerController answerController;
	
	
	@Test
	public void test1() {
		Issue record = new Issue();
		record.setIssueContext("测试添加1528");
		record.setHeadline("标题");
		record.setPublishTime(new Date());
		record.setUserId(30);
		record.setVideoId(11);
		
		int ia = issueDao.insert(record);
		System.out.println(ia);
	}
	
	
	@Test
	public void test2() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(issueController).build();
		
		RequestBuilder registRequest = MockMvcRequestBuilders.get("/user/findbyvideoid/")
				.param("videoId", "11");
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
	
	@Test
	public void test3() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(answerController).build();
		
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/addanswer/")
				.param("userId", "30")
				.param("issueId", "2")
				.param("context", "测试回答问题2");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		//将返回的json字符串转成ydmaResult对象
		ObjectMapper mapper = new ObjectMapper();
		YdmaResult ydmaResult = mapper.readValue(content, YdmaResult.class);
		//断言
		Assert.assertEquals(ydmaResult.getCode(), YdmaConstant.SUCCESS);
		Assert.assertEquals(ydmaResult.getMsg(), YdmaConstant.INSERT_SUCCESS_MSG);
		
	}
	
}
