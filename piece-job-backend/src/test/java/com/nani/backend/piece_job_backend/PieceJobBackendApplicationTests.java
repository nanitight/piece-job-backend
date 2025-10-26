package com.nani.backend.piece_job_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = DotEnvInitializer.class)
class PieceJobBackendApplicationTests {

	@Test
	void contextLoads() {
	}
    
    @BeforeEach
    public void LoadDotEnv(){
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(e ->
                System.setProperty(e.getKey(),e.getValue()));
    }

}
