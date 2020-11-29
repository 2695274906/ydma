package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})
public class RunNoteCollect {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunNoteCollect.class, args);
	}
}
