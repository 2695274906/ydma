package cn.xdl.ydma;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"cn.xdl.ydma.dao"})
public class RunBoot {
	public static void main(String[] args) {
		 SpringApplication.run(RunBoot.class, args);
//		 DataSource  ds = acc.getBean("dataSource", DataSource.class);
//		 System.out.println(ds);
	
	}
}
