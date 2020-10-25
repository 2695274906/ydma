package cn.xdl.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"cn.xdl.ydma.dao"})
public class RunVideoBoot {
	public static void main(String[] args) {
		SpringApplication.run(RunVideoBoot.class, args);
	}

}
