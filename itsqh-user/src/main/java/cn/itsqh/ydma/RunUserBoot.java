package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import cn.itsqh.ydma.dao.UserMapper;
import cn.itsqh.ydma.entity.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})

public class RunUserBoot {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunUserBoot.class, args);
	}
	/**
	* @author:zhangsan
	* @Description: 工厂模式
	* @param:
	 *@return:
	*/
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}
    LinkedBlockingQueue
}
