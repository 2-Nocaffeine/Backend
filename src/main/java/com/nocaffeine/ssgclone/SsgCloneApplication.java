package com.nocaffeine.ssgclone;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(servers = {
		@Server(url = "http://localhost:8080/", description = "로컬"),
		@Server(url = "https://nocaffein.shop/", description = "노카페인")
})
@EnableJpaAuditing
@SpringBootApplication
public class SsgCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsgCloneApplication.class, args);
	}

}
