package com.food.api.integration;

import com.food.api.services.RecipesRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class RecipesControllerTest extends AbstractIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	Gson gson;

	@Autowired
	RecipesRepository repository;

	@Before
	public void clear() {
		repository.deleteAll();
	}
}
