package cn.xdl.ydma.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.util.StringUtil;

import cn.xdl.ydma.dao.UserMapper;
import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.service.UserService;
import cn.xdl.ydma.util.JWTUtil;
import cn.xdl.ydma.util.PasswordUtil;
import cn.xdl.ydma.util.YdmaConstant;
import cn.xdl.ydma.util.YdmaResult;



/**
* @author:zhangsan
* @Description:
* @param:
 *@return:
*/
@Service
public class UserServiceImpl implements UserService{
	//注入比实现类更灵活
	@Autowired
//	private UserMapper userDao;
	private UserMapper userDao;
	//根据id查询用户
	@Override
	public YdmaResult<User> load(int id) {
		//User user = userDao.selectByPrimaryKey(id);
		//如果没有这个方法，则需要自己写方法
		YdmaResult<User> result = new YdmaResult<User>();
		//通过用户id查询用户信息
		User user = userDao.selectByPrimaryKey(id);
		//如果user为空  error1:"未找到查询记录";
		if(user==null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOAD_ERROR_MSG);
			return result;
		}
		//SUCCESS 0:查询成功
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.LOAD_SUCCESS_MSG);
		result.setData(user);
		
		return result;
	}
    /**
    * @author:zhangsan
    * @Description:根据用户名和密码添加用户
    * @param:
     *@return:
    */
	@Override
	public YdmaResult add(String username, String password) {
		//参数格式检查
		YdmaResult result = new YdmaResult();
		//如果用户名或密码一个为空，那么就是   9:请求参数格式错误
		if(StringUtil.isEmpty(username)||StringUtils.isEmpty(password)) {
			result.setCode(YdmaConstant.ERROR9);
			result.setMsg(YdmaConstant.PARAMS_ERROR_MSG);
			return result;
		}
		//检查用户是否占用 
		User  user = userDao.selectByName(username);
		//如果用户不为空，1:用户被占用
		if(user!=null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.REGIST_NAME_ERROR_MSG);
			return result;
		}
		//创建用户对象
		user = new User();
		user.setName(username);
		/*密码加密，采用MD5(密码+盐)
        生成一个随机盐*/
		String salt = PasswordUtil.salt();
        //保存起来 d5Password加盐后密码
		user.setSalt(salt);
		System.out.println("保存的盐是"+user.getSalt());
		String md5Password = PasswordUtil.md5(password+salt);
		user.setPassword(md5Password);
		//设置注册时间 new Date()表示系统当前时间
		user.setRegtime(new Date());
		userDao.insertSelective(user);
		// 0:注册成功
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.REGIST_SUCCESS_MSG);
		return result;
	}
    /**
    * @author:zhangsan
    * @Description:登录验证
    * @param:
     *@return:
    */
	@Override
	public YdmaResult check(String username, String password) {
		YdmaResult result = new YdmaResult();
		//参数格式检查 如果用户名或密码有一个为空，则是9：请求格式参数错误
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			result.setCode(YdmaConstant.ERROR9);
			result.setMsg(YdmaConstant.PARAMS_ERROR_MSG);
			return result;
		}
		//先比对用户名 用户名不一致：1：用户名错误
		User user = userDao.selectByName(username);
		if(user == null) {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.LOGIN_NAME_ERROR_MSG);
			return result;
		}
		//用户名正确再比对密码
		String md5Password = PasswordUtil.md5(password+user.getSalt());
		//如果输入的密码加密后和数据中加密后的密码不相等，则报错2：密码错误
		if(!user.getPassword().equals(md5Password)) {
			result.setCode(YdmaConstant.ERROR2);
			result.setMsg(YdmaConstant.LOGIN_PASSWORD_ERROR_MSG);
			return result;
		}
		//返回一个token令牌
		String token = JWTUtil.sign(user);
		result.setData(token);
		result.setCode(YdmaConstant.SUCCESS);
		result.setMsg(YdmaConstant.LOGIN_SUCCESS_MSG);
		//TODO 记录登录日志信息
		return result;
	}

	/**
	* @author:zhangsan
	* @Description: token验证
	* @param:
	 *@return:
	*/
	@Override
	public YdmaResult token(String token) {
		YdmaResult result = new YdmaResult();
		//验证token，如果为true,表示验证通过，否则验证失败
		boolean ok = JWTUtil.verify(token);
		if(ok) {
			result.setCode(YdmaConstant.SUCCESS);
			result.setMsg(YdmaConstant.TOKEN_SUCCESS5_MSG);
		}else {
			result.setCode(YdmaConstant.ERROR1);
			result.setMsg(YdmaConstant.TOKEN_ERROR_MSG);
		}
		return result;
	}

}
