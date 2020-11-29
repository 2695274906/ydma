package cn.itsqh.ydma.entity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.itsqh.ydma.entity.User;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.entity.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user/findUser")
	public YdmaResult findUserById(int id){
		return userService.findUserById(id);
	}
	
	@PostMapping("/user/regist")
	public YdmaResult regist(String name,String password) {
		return userService.addUser(name, password);
	}
	
	@PostMapping("/user/login")
	public YdmaResult userLogin(String name,String password) {
		return userService.findUserByNameAndPassword(name, password);
	}
	/**
	 * 	根据旧密码修改新密码
	 * @param name	账号
	 * @param oldPassword	旧密码
	 * @param newPassword1	新密码
	 * @param newPassword2
	 * @return	
	 */
	@PostMapping("/user/updatapassword")
	public YdmaResult userUpdatePassword(String name, String oldPassword, String newPassword1, String newPassword2) {
		return userService.updateByPrimaryKeySelective(name, oldPassword, newPassword1, newPassword2);
	}
	/**
	 * 修改个人信息
	 * @param name	账号
	 * @param nick_name	昵称
	 * @param position	职位
	 * @param sex	性别
	 * @param location	所在地
	 * @param signature	个性签名
	 * @param image	头像路径
	 * @return	
	 */
	@PostMapping("/user/updataAll")
	public YdmaResult userUpdateAll(String name, String nick_name, String position, String sex, String location, String signature,
			String image) {
		return userService.updateByAll(name,nick_name, position, sex, location, signature, image);
	}
}
