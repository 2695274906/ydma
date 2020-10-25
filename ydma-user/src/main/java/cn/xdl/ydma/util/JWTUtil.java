package cn.xdl.ydma.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import cn.xdl.ydma.entity.User;

public class JWTUtil {
	//默认单位是毫秒 15分钟  
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	private static final String TOKEN_SECRET = "dsdsa221";//密钥很宝贵 用于在服务器端的签发和验证
	
	/**
	 * 生成签名，15分钟过期
	 * @param **username**
	* @param **password**
	* @return 
	 */
	public static String sign(User user) {
	    try {
	        // 设置过期时间EXPIRE_TIME System.currentTimeMillis() 获取系统当前时间
	        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
	        // 私钥和加密算法
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        // 设置头部信息包括两部分：类型和加密算法
	        Map<String, Object> header = new HashMap<>(2);
	        header.put("typ", "JWT");//声明类型，这里是jwt
	        header.put("alg", "HS256");// 声明加密的算法，通常直接使用HMAC SHA256
	        // 返回token字符串
	        //token包括三部分：第一部分就是Header;
	        //第二部分：载荷的有效信息（内部又包括三部分：标准声明（不强制使用）、公共声明、私有声明）；第三部分：签证信息
	        return JWT.create()
	                .withHeader(header)//头部
	                .withClaim("aud", user.getName())//aud：接收jwt的一方
	                .withClaim("uid", user.getId())//自定义数据uid
	                .withClaim("uname", user.getName())
	                .withExpiresAt(date)//过期时间
	                .sign(algorithm);//签证的作用就是验证前两个部分的合法性的
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 *检验token是否正确
	 * @param **token**
	 * @return
	 */
	public static boolean verify(String token){
	    try {
	    	//私钥和加密算法
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        //JWTVerifier颁发者
	        JWTVerifier verifier = JWT.require(algorithm).build();
	        verifier.verify(token);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}
	/**
	 *从token解析出uid信息
	 * @param token
	 * @param key
	 * @return
	 */
	public static int parseTokenUid(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("uid").asInt();
	}
	//从token中解析uname
	public static String  parseTokenUname(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("uname").asString();
	}
	
	
	public static void main(String[] args) {
		User user = new User();
		user.setId(6);
		user.setName("paopao2");
		String token=sign(user);
		System.out.println("生成的token是："+token);
		
		//token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJwYW9wYW8yIiwidWlkIjo1LCJleHAiOjE1NjUwNzM5NzV9.6rCooL1COcmAzkvZ_VnO_mN_sWruGEe0P8-iSurKjcs";
		int  s = parseTokenUid(token);
		String s1 = parseTokenUname(token);
		System.out.println("解析token后的uid结果是"+s);
		System.out.println("解析token后的uname结果是"+s1);
	}
	
}
