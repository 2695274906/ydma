package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJunit {
	
	@BeforeClass
	public static void initClass() {
		System.out.println("初始化类");
	}
	
	@Before//每次执行@Test方法前都先执行@Before
	public void init() {
		System.out.println("初始化");
	}
	
	@After//每次执行@Test方法后都先执行@After
	public void destroy() {
		System.out.println("销毁");
	}
	
	@AfterClass
	public static void destroyClass() {
		System.out.println("销毁类");
	}
	
	@Test
	public void test1() {
		System.out.println("----执行test1测试----");
		String s = null;
		s.length();
	}
	
	@Test
	public void test2() {
		System.out.println("----执行test2测试----");
	}
	
	@Test
	public void test3() {
		System.out.println("----执行test3测试----");
		String actual = "tom";
		Assert.assertEquals("TOM", actual);
	}
	
}
