package cn.itsqh.ydma.entity.service;

import cn.itsqh.ydma.entity.User;
import cn.itsqh.ydma.entity.YdmaResult;

public interface UserService {
	
	/**
	 * 	根据用户id查询用户
	 * @param id	用户id
	 * @return
	 */
	public YdmaResult findUserById(int id);
	
	
	/**
	 * 	添加账号和密码
	 * @param name	账号
	 * @param password	密码
	 * @return	YdmaResult对象
	 */
	public YdmaResult addUser(String name,String password);
	
	/**
	 * 	根据账号查询用户
	 * @param name	账号
	 * @return	User对象
	 */
	public User findByName(String name);
	
	/**
	 * 	根据账号和密码查询用户
	 * @param name	账号
	 * @param password	密码
	 * @return	YdmaResult对象
	 */
	public YdmaResult findUserByNameAndPassword(String name, String password);
	
	/**
	 * 	根据旧密码修改新密码
	 * @param oldPassword	旧密码	
	 * @param newPassword1	第一次输入的新密码
	 * @param newPassword2	第二次输入的新密码
	 * @return	YdmaResult对象
	 */
	public YdmaResult updateByPrimaryKeySelective(
			String name,
			String oldPassword,
			String newPassword1,
			String newPassword2);

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
	public YdmaResult updateByAll(
			String name,
			String nick_name,
			String position,
			String sex,
			String location,
			String signature,
			String image);
	
	
}
