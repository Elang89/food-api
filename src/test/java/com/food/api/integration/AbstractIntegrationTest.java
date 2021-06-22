package com.food.api.integration;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.food.api.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    private static final int PG_PORT = 5432;

    @ClassRule
    public static GenericContainer<?> pgDb = 
        new GenericContainer<>("debezium/postgres:13-alpine")
            .withExposedPorts(PG_PORT)
            .withEnv("POSTGRES_USER", "root")
            .withEnv("POSTGRES_PASSWORD", "password")
            .withEnv("POSTGRES_DB", "food");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String user = pgDb.getEnvMap().get("POSTGRES_USER");
            String password = pgDb.getEnvMap().get("POSTGRES_PASSWORD");
            String db = pgDb.getEnvMap().get("POSTGRES_DB");

            String endpoint = String.format("spring.datasource.url=jdbc:tc:postgresql://%s:%s/%s?user=%s&password=%s",
                pgDb.getContainerIpAddress(),
                pgDb.getMappedPort(PG_PORT),
                db,
                user,
                password
            );

            TestPropertyValues.of(endpoint).applyTo(configurableApplicationContext);
        }

    }

}
