package com.ssafy.side;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SideApplication.class, args);
	}

}
