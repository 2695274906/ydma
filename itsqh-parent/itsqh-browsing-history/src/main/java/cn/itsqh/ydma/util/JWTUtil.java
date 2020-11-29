package cn.itsqh.ydma.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;



public class JWTUtil {
	
	public static int parseTokenUid(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("uid").asInt();
	}
	
	
}
