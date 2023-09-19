package m22t.ansdlsrb.m22tProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//필터 또는 인터셉터 적용뒤 (exclude = SecurityAutoConfiguration.class) 지우기
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class M22tProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(M22tProjectApplication.class, args);
	}

}
