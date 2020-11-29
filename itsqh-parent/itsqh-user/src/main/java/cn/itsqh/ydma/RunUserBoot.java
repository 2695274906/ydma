package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

import cn.itsqh.ydma.dao.UserMapper;
import cn.itsqh.ydma.entity.User;

@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})
@ServletComponentScan
public class RunUserBoot {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunUserBoot.class, args);
		
	}
}
