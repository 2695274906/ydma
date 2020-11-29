package cn.itsqh.ydma.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;




public class JWTUtil {
	
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	private static final String TOKEN_SECRET = "dsdsa221";
	

	
	
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
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJqYXZhMjkiLCJ1aWQiOjM4LCJleHAiOjE1NjExMTQ0OTd9.hlfBi5dazoOHmkYPy9ZlEGLAGhrLd4KtRvd77U4A0hc";
		int  s = parseTokenUid(token);
		System.out.println(s);
	}
	
}
