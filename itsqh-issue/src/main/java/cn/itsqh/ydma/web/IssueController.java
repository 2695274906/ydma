package cn.itsqh.ydma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.service.IssueService;

@RestController
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class IssueController {

	@Autowired
	private IssueService issueDao;
	
	/**
	 * 	添加提问
	 * @param userid	用户id
	 * @param videoid	视频id
	 * @param context	提问
	 * @param headline	标题
	 * @return
	 */
	@PostMapping("/user/addissue")
	public YdmaResult addIssue(int userid,int videoid,String context,String headline) {
		
		return issueDao.addIssue(userid, videoid, context, headline);
	}
	
	/**
	 * 		查询提问
	 * @param videoId	视频id
	 * @param page	
	 * @param size
	 * @return
	 */
	@GetMapping("/user/findbyvideoid")
	public YdmaResult findByVideoId(
			@RequestParam("videoId") int videoId,
			@RequestParam(name="page",required=false,defaultValue="1")int page,
			@RequestParam(name="size",required=false,defaultValue="8")int size) {
		
		return issueDao.findByVideoId(videoId, page, size);
	}
}
