package cn.itsqh.ydma.entity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

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
	
	//
	@Autowired
	private RestTemplate restemplate;
	
	@Override
	public  YdmaResult findUserByToken(String token) {
		YdmaResult result = new YdmaResult();
		int uid = JWTUtil.parseTokenUid(token);
		User user = userDao.selectByPrimaryKey(uid);
		if(user == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_ERROR1_MSG);
			return result;
		}else{
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
			result.setData(user);
			return result;
		}
	}
	
	//登录功能
	@Override
	public  YdmaResult findLoginHistoryByToken(String token) {
		YdmaResult result = new YdmaResult();
		//从token中解析出uid;
		int uid = JWTUtil.parseTokenUid(token);
		User user = userDao.selectByPrimaryKey(uid);
		if(user == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_ERROR1_MSG);
			return result;
		}else{
			int userId = user.getId();
			String url = "http://localhost:8888/login/findhistory?userId="+userId;
			YdmaResult loginHistoryResult =restemplate.getForObject(url, YdmaResult.class);
			if(loginHistoryResult.getCode()==YdmaConstant.SUCCESS) {
				List loginHistoryList = (List)loginHistoryResult.getData();
				user.setLoginHistorys(loginHistoryList);
			}
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
			result.setData(user);
			return result;
		}
	}
	
	//登录操作
	@Override
	public YdmaResult findUserByNameAndPassword(String name, String password) {
		YdmaResult result = new YdmaResult();
		//进行登录操作
		User user = findByName(name);
		//System.out.println("user = " + user);
		if(user == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_ERROR1_MSG);
			return result;
		}
		//查询有该用户,进行密码核对
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
		//	登录成功后添加登录记录    time:7/17  8:52
		//	跨域
		System.out.println(user.getId());
		String url = "http://localhost:7011/login/addhistory?userId="+user.getId();
		YdmaResult courseResult =restemplate.getForObject(url, YdmaResult.class);
		if(courseResult.getCode()==YdmaConstant.SUCCESS) {
			System.out.println("登录记录已添加");
		}
		
		return result;
	}
	//注册用户
	@Override
	public YdmaResult addUser(String name, String password) {
		YdmaResult result = new YdmaResult();
		//	检查参数中用户名或密码是否为空 ，5:输入参数不合法
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			result.setCode(YdmaConstant.ERROR5);
			result.setMsg(YdmaConstant.PARAM_ERROR5_MSG);
			return result;
		}
		// 	检查用户名是否存在   1:用户名已存在
		User user = findByName(name);
		if (user != null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.REGIST_ERROR1_MSG);
			return result;
		}
		// 	添加用户操作
		user = new User();
		user.setName(name);
		user.setRegtime(new Date());//new Date() 表示当前时间
		//	生成一把随机盐
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
			result.setCode(YdmaConstant.ERROR2);
			result.setMsg(YdmaConstant.UPDATE_PASSWORD_ERROR2_MSG);
			return result;
		}
		//		修改数据库中的密码
		User newUser = new User();
		//		生成盐
		String salt = PasswordUtil.salt();
		//	给密码加盐
		String newPassword = PasswordUtil.md5(newPassword1+salt);
		//System.out.println("加密的密码:" + newPassword);
		newUser.setPassword(newPassword);
		newUser.setId(id);
		newUser.setSalt(salt);
		int ia = userDao.updateByPrimaryKeySelective(newUser);
		//		影响行数大于0 ,修改成功
		if(ia > 0 ) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.UPDATE_PASSWORD_SUCCESS_MSG);
			return result;
		}
		//	 否则就修改密码失败
		result.setCode(YdmaConstant.ERROR3);
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


	@Override
	public YdmaResult updateBytoken(String token, String nick_name, String position, String sex, String location,
			String signature) {
		YdmaResult result = new YdmaResult();
		int uid = JWTUtil.parseTokenUid(token);
		User record = new User();
		record.setNickName(nick_name);
		record.setPosition(position);
		record.setSex(sex);
		record.setLocation(location);
		record.setSignature(signature);
		record.setId(uid);
		int ia = userDao.updateByPrimaryKeySelective(record);
		System.out.println("修改个人信息:---------------" + ia);
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


	

}
