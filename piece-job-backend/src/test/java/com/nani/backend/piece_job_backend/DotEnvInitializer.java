package com.nani.backend.piece_job_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class DotEnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.load();
        ConfigurableEnvironment env = applicationContext.getEnvironment() ;

        dotenv.entries().forEach(entry ->
                env.getSystemProperties().put(entry.getKey(),entry.getValue()));
    }
}
