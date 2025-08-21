package com.xavier.servimatchbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@SpringBootApplication
@Modulithic(
		sharedModules = {"common"},
		systemName = "ServiMatch"
)
public class ServimatchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServimatchBackendApplication.class, args);
	}

}
