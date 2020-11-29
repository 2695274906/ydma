package cn.text;

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

import cn.itsqh.ydma.RunBrowsingBoot;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.impl.BrowsingHistoryServiceImpl;
import cn.itsqh.ydma.web.BrowsingHistoryController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RunBrowsingBoot.class})
public class borwTest {

	@Autowired
	private BrowsingHistoryController controller;
	
	@Test
	public void test1() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
			
		RequestBuilder registRequest = MockMvcRequestBuilders.post("/brohis/findByUserId")
				.param("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJRV0UiLCJ1aWQiOjI1LCJleHAiOjE1NjMzMjg0MzZ9.oQzGs4q1G-cM4LJAYVCX3G3J_nUV894E4ZLOYdBtaXc");
		MvcResult result = mock.perform(registRequest).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		
		
		
	}
}
