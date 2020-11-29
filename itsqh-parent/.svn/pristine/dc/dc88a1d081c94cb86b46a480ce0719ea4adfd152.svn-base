package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.EvaluateService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class EvaluateController {

	@Autowired
	private EvaluateService evaluateService;
	
	/**
	 * 	添加评论
	 * @param usetid	用户id
	 * @param videoid	视频id
	 * @param context	评论内容
	 * @return
	 */
	@PostMapping("/user/addevaluate")
	public YdmaResult addEvaluate(@RequestParam("userid")int userid,
			@RequestParam("videoid")int videoid,
			@RequestParam("context")String context) {
		//System.out.println("进入添加方法");
		return evaluateService.addEvaluate(userid, videoid, context);
	}
	
	/**
	 * 	根据视频id查询评论
	 * @param id 
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/user/findevaluate")
	public YdmaResult findEvaluate(
			@RequestParam("id")int id,
			@RequestParam(name="page",defaultValue="1",required=false)int page,
			@RequestParam(name="size",required=false,defaultValue="8")int size) {
		
		return evaluateService.findByVideoId(id, page, size);
	}
	
}
