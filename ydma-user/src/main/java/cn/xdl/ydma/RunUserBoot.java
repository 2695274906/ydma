package cn.xdl.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication 
@MapperScan(basePackages= {"cn.xdl.ydma.dao"}) //组件扫描
@ServletComponentScan //扫描servlet组件包括servlet listener filter
public class RunUserBoot {

	public static void main(String[] args) {
		SpringApplication.run(RunUserBoot.class, args);
	}

}
