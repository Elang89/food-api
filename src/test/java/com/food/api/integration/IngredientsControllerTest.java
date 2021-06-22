package com.food.api.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class IngredientsControllerTest extends AbstractIntegrationTest {
    
    @Autowired
    TestRestTemplate restTemplate;


    @Before
    public void clear() {
        System.out.println("MY ASS!");
    }

    @Test
    public void findAll() {
        assertThat(1).isEqualTo(1);
    }
}
