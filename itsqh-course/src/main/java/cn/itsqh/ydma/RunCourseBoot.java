package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})
//@EnableCaching//	开启Spring cache注解支持
public class RunCourseBoot {
	public static void main(String[] args) {
		
		SpringApplication.run(RunCourseBoot.class, args);
	}
}
