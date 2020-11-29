package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})
public class RunNoteBoot {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunNoteBoot.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
