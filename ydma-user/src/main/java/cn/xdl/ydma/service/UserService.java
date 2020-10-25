package cn.xdl.ydma.service;


import cn.xdl.ydma.entity.User;
import cn.xdl.ydma.util.YdmaResult;

public interface UserService {
	//根据用户Id查询user用户
	public YdmaResult<User> load(int id);
	//根据用户名和密码注册用户
	public YdmaResult add(String username,String password);
	//登录验证
	public YdmaResult check(String username,String password);
	//加入了token指令
	public YdmaResult token(String token);
	
	
	
}
