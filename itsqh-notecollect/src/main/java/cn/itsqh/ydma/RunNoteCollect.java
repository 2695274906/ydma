package cn.itsqh.ydma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages= {"cn.itsqh.ydma.dao"})
@CrossOrigin(origins= {"*"},methods= {RequestMethod.GET,RequestMethod.POST})
public class RunNoteCollect {
	
	public static void main(String[] args) {
		
		SpringApplication.run(RunNoteCollect.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
