package cn.itsqh.ydma.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;




public class JWTUtil {
	
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	private static final String TOKEN_SECRET = "dsdsa221";
	
	/**
	 * 生成签名，15分钟过期
	 * @param **username**
	* @param **password**
	* @return
	 */

	
	/**
	   *      检验token是否正确
	 * @param **token**
	 * @return
	 */
	public static boolean verify(String token){
	    try {
	        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
	        JWTVerifier verifier = JWT.require(algorithm).build();
	        verifier.verify(token);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}
	
	/**
	   *      从token解析出uid信息
	 * @param token
	 * @param key
	 * @return
	 */
	public static int parseTokenUid(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("uid").asInt();
	}
	
	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhYWEiLCJ1aWQiOjMsImV4cCI6MTU2MzM1NzE0NX0.iic-9A1G1od8kMxEZmDiUfws-yUpNMkoDkx3xD1en6Q";
		int  s = parseTokenUid(token);
		System.out.println(s);
	}
	
}
