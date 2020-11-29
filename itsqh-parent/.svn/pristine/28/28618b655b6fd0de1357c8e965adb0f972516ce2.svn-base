package redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedis {
	
	@Test
	public void test1() {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println(jedis.ping());
	}
	
	@Test
	public void test2() {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.set("name", "sqh");
		jedis.getSet("age", 18+"");
		String name = jedis.get("name");
		System.out.println(name);
		String age = jedis.get("age");
		System.out.println(age);
		jedis.close();
	}
	
	
	@Test
	public void test3() {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.hset("list1", "xiaoxiao", "yanyan");
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", "yangyang");
		map.put("age", 18+"");
		jedis.hmset("dept1", map);
		jedis.close();
	}
	
	@Test
	public void test4() {
		Jedis jedis = new Jedis("localhost",6379);
		for(int i = 'A'; i <= 'Z'; i++) {
			jedis.rpush("list", (char)i+"");
		}
		jedis.expire("list", 5);
		jedis.close();
	}
}
