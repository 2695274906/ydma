package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@PostMapping("/user/addanswer/")
	public YdmaResult addAnswer(int userId, int issueId, String context) {
		return answerService.addAnswer(userId, issueId, context);
	}
	
}
