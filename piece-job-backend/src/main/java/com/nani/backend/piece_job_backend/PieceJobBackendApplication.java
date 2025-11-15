package com.nani.backend.piece_job_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PieceJobBackendApplication {

	public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.load();
//        dotenv.entries().forEach(e ->
//                System.setProperty(e.getKey(),e.getValue()));
		SpringApplication.run(PieceJobBackendApplication.class, args);
	}

}
