package com.xavier.servimatchbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ApplicationModuleTest
class ServimatchBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void verifyModularStructure() {
		ApplicationModules modules = ApplicationModules.of(ServimatchBackendApplication.class);

		// Verify that modules are detected correctly
		assertThat(modules.stream()).isNotEmpty();

		// This will throw an exception if there are architectural violations
		modules.verify();

	}
	

}
