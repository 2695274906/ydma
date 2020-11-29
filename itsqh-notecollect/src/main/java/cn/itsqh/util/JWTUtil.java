package cn.itsqh.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;




public class JWTUtil {
	
	private static final long EXPIRE_TIME = 15 * 60 * 1000;
	private static final String TOKEN_SECRET = "dsdsa221";
	

	
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
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJqYXZhMjkiLCJ1aWQiOjM4LCJleHAiOjE1NjExMTQ0OTd9.hlfBi5dazoOHmkYPy9ZlEGLAGhrLd4KtRvd77U4A0hc";
		int  s = parseTokenUid(token);
		System.out.println(s);
	}
	
}
