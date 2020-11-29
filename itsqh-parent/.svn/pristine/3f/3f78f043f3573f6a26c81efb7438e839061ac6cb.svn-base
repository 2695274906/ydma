package cn.itsqh.ydma.entity.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.itsqh.ydma.dao.UserMapper;
import cn.itsqh.ydma.entity.User;
import cn.itsqh.ydma.entity.YdmaConstant;
import cn.itsqh.ydma.entity.YdmaResult;
import cn.itsqh.ydma.entity.service.UserService;
import cn.itsqh.ydma.util.JWTUtil;
import cn.itsqh.ydma.util.PasswordUtil;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userDao;

	
	@Override
	public YdmaResult findUserByNameAndPassword(String name, String password) {
		YdmaResult result = new YdmaResult();
		//		进行登录操作
		User user = findByName(name);
		//System.out.println("user = " + user);
		if(user == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_ERROR1_MSG);
			return result;
		}
		//		查询有该用户,进行密码核对
		String md5Password = PasswordUtil.md5(password+user.getSalt()); 
		if(!md5Password.equals(user.getPassword())) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_ERROR1_MSG);
			return result;
		}
		String token = JWTUtil.sign(user);
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
		result.setData(token);
		return result;
	}
	
	
	@Override
	public YdmaResult addUser(String name, String password) {
		YdmaResult result = new YdmaResult();
		//	检查参数是否为空
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			result.setCode(YdmaConstant.ERROR5);
			result.setMsg(YdmaConstant.PARAM_ERROR5_MSG);
			return result;
		}
		// 	检查用户名是否存在
		User user = findByName(name);
		if (user != null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.REGIST_ERROR1_MSG);
			return result;
		}
		// 	添加用户操作
		user = new User();
		user.setName(name);
		user.setRegtime(new Date());
		//	生成盐
		String salt = PasswordUtil.salt();
		//	给密码加盐
		String md5Password = PasswordUtil.md5(password+salt);
		user.setPassword(md5Password);
		user.setSalt(salt);
		//	添加用户
		userDao.insertSelective(user);
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.REGIST_SUCCESS_MSG);
		return result;
	}


	@Override
	public User findByName(String name) {
		User user = userDao.selectByName(name);
		//System.out.println("findByName() =====>  user   = " +user);
		return user;
	}


	//	影响行数为0
	@Override
	public YdmaResult updateByPrimaryKeySelective(String name,String oldPassword, String newPassword1, String newPassword2) {
		YdmaResult result = new YdmaResult();
		//	判断两次输入密码是否一致
		if(!newPassword1.equals(newPassword2)) {
			//	两次输入不一致
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.UPDATE_PASSWORD_ERROR1_MSG);
			return result;
		}
		//		进行旧密码的验证
		User user = findByName(name);
		String  password = user.getPassword();
		int id = user.getId();
		//		数据库密码和输入密码不一致
		oldPassword = PasswordUtil.md5(oldPassword + user.getSalt());
		//System.out.println(password+"==密码比较=="+oldPassword);
		if(!oldPassword.equals(password)) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.UPDATE_PASSWORD_ERROR2_MSG);
			return result;
		}
		//		修改数据库中的密码
		User newUser = new User();
		//		生成盐
		String salt = PasswordUtil.salt();
		//	给密码加盐
		String newPassword = PasswordUtil.md5(password+salt);
		//System.out.println("加密的密码:" + newPassword);
		newUser.setPassword(newPassword);
		newUser.setId(id);
		int ia = userDao.updateByPrimaryKeySelective(newUser);
//		System.out.println("修改结果   ---------------" + ia);
//		System.out.println();
		//		影响行数大于0 ,修改成功
		if(ia > 0 ) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.UPDATE_PASSWORD_SUCCESS_MSG);
			return result;
		}
		//	 否则就修改密码失败
		result.setCode(YdmaConstant.ERROR1);
		result.setMsg(YdmaConstant.UPDATE_PASSWORD_ERROR3_MSG);
		return result;
		
	}


	@Override
	public YdmaResult updateByAll(String name, String nick_name, String position, String sex, String location, String signature,
			String image) {
		YdmaResult result = new YdmaResult();
		User record = new User();
		record.setNickName(nick_name);
		record.setPosition(position);
		record.setSex(sex);
		record.setLocation(location);
		record.setSignature(signature);
		record.setImage(image);
		record.setName(name);
		int ia = userDao.updateByPrimaryNameSelective(record);
		//System.out.println("修改个人信息:---------------" + ia);
		//		影响行数等于0, 修改个人信息失败
		if(ia == 0) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.UPDATE_USER_ERROR1_MSG);
			return result;
		}
		//	修改用户信息成功                  
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.UPDATE_USER_SUCCESS_MSG);
		return result;
	}


	@Override
	public YdmaResult findUserById(int id) {
		YdmaResult result = new YdmaResult();
		User user = userDao.selectByPrimaryKey(id);
		if(user != null) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.SELECT_SUCCESS_MSG);
			result.setData(user);
		}else {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.SELECT_ERROR1_MSG);
		}
		return result;
	}

	

}
