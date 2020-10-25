package cn.xdl.ydma.util;

import java.security.MessageDigest;
import java.util.UUID;

public class PasswordUtil {
	
	//调用md5方法对需要加密的对象进行加密，然后返回一个加密后的对象数组，针对字节进行加密
	public static String md5(String s) {
	    try {
	    	//MessageDigest(String algorithm) 创建具有指定算法名称的MessageDigest 实例对象。 
	    	//MessageDigest为应用程序提供信息摘要算法的功能 getInstance(String algorithm)  生成实现指定摘要算法的 MessageDigest 对象。
	        MessageDigest md = MessageDigest.getInstance("MD5");
	       //digest() 通过执行诸如填充之类的最终操作完成哈希计算。
	        byte[] bytes = md.digest(s.getBytes("utf-8"));//s.getBytes("utf-8")得到一个字节数组
	        //System.out.println(new String(bytes));//字节转字符串 这种方式容易乱码
	        return toHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	//为了避免乱码，toHex()方法将字节转化为字符串 都转化为十六进制 
	private static String toHex(byte[] bytes) {
		//String.toCharArray(),返回的类型是字节型数组
		//当使用final修饰基本类型变量时，不能对基本类型变量重新赋值，因此基本类型变量不能被改变。
		//但对于引用类型的变量而言，它保存的仅仅是一个引用，final只保证这个引用所引用的地址不会改变，即一直引用同一个对象，但这个对象完全可以发生改变。
		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		//final char[] HEX_DIGITS = "89.9141431414".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
	
	public static String salt(){
		UUID uuid = UUID.randomUUID();
		String[] arr = uuid.toString().split("-");
		return arr[0];
	}
	
	
	public static void main(String[] args) {
		System.out.println("密码123调用MD5方法进行加密后密文是："+md5("123"));
		System.out.println("密码1234567890调用MD5方法进行加密后密文是："+md5("1234567890"));
		//		UUID uuid = UUID.randomUUID();
//		System.out.println(uuid);
	}
	
}
