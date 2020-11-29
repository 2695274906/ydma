package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//启动类
@SpringBootApplication
@MapperScan(basePackages= {	"cn.itsqh.ydma.dao"}) //扫描cn.itsqh.ydma.dao下的所有包
public class RunDirectionBoot {
	
	public static void main(String[] args) {
		//RunDirectionBoot.class为启动的类对象
		SpringApplication.run(RunDirectionBoot.class, args);
	}
	//服务间项目之间的调用ydma-direction程序需要获取ydma-course程序的资源时，
	//需要通过发送rest服务获取返回的响应结果。
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
