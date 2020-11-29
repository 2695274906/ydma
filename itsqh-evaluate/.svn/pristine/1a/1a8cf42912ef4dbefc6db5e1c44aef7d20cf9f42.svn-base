package dao;

import java.util.Date;
import java.util.List;

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

import cn.itsqh.ydma.RunEvaluateBoot;
import cn.itsqh.ydma.dao.EvaluateMapper;
import cn.itsqh.ydma.entity.Evaluate;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.EvaluateService;
import cn.itsqh.ydma.web.EvaluateController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunEvaluateBoot.class})
public class TestEvaluateDao {
	
	@Autowired
	private EvaluateMapper evaluateDao;
	
	@Autowired
	private EvaluateController evaluateController;
	
	@Test
	public void test1() {
		Evaluate evaluate = new Evaluate();
		evaluate.setPublishTime(new Date());
		evaluate.setUseId(30);
		evaluate.setVideoId(11);
		evaluate.setContext("测试添加评价");
		evaluate.setLikeCount(0);
		int ia = evaluateDao.insert(evaluate);
		System.out.println(ia>0?"添加成功":"添加失败");
	}
	
	
	@Test
	public void test2() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(evaluateController).build();
		
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/user/addevaluate/")
				.param("userid", "30")
				.param("videoid", "11")
				.param("context", "添加测试1720");
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
	
	@Test
	public void test3() {
		List<Evaluate> list = evaluateDao.selectByVideoId(11);
		list.forEach(action -> System.out.println(action.getId() + " " + action.getContext()));
	}
}
