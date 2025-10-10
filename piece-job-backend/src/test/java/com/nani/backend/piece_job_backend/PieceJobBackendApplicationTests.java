package com.nani.backend.piece_job_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = DotEnvInitializer.class)
class PieceJobBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
