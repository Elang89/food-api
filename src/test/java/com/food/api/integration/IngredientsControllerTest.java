package com.food.api.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import static org.rnorth.visibleassertions.VisibleAssertions.*;
import com.food.api.models.ingredients.Ingredient;
import com.food.api.models.ingredients.IngredientListResponse;
import com.food.api.models.ingredients.IngredientRequest;
import com.food.api.models.ingredients.IngredientResponse;
import com.food.api.models.ingredients.UpdateIngredient;
import com.food.api.models.ingredients.UpdateIngredientRequest;
import com.food.api.services.IngredientsRepository;
import com.google.gson.Gson;

public class IngredientsControllerTest extends AbstractIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	IngredientsRepository repository;

	@Autowired
	Gson gson;


	@Before
	public void clear() {
		repository.deleteAll();
	}

	@Test
	public void testFindAllIngredients() {
		info("Getting all ingredients...");

		String resource = "/ingredients";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<IngredientListResponse> response = restTemplate.exchange(resource,
				HttpMethod.GET, entity, IngredientListResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getIngredients().size()).isBetween(0, 200);

	}

	@Test
	public void testFindOneIngredientById() {
		Ingredient ingredient = new Ingredient("ham");
		String resource = "/ingredients/" + ingredient.getId();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		info("Creating ingredient...");


		repository.save(ingredient);


		info("Retrieving ingredient...");

		ResponseEntity<IngredientResponse> response =
				restTemplate.exchange(resource, HttpMethod.GET, entity, IngredientResponse.class);

		Ingredient retrievedIngredient = response.getBody().getIngredient();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retrievedIngredient.getId()).isEqualTo(ingredient.getId());
	}

	@Test
	public void testCreateIngredient() {
		Ingredient ingredient = new Ingredient("ham");
		IngredientRequest payload = new IngredientRequest(ingredient);
		String resource = "/ingredients";
		String request = gson.toJson(payload);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request, headers);

		info("Creating ingredient...");

		ResponseEntity<IngredientResponse> response =
				restTemplate.exchange(resource, HttpMethod.POST, entity, IngredientResponse.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getIngredient().getId()).isEqualTo(ingredient.getId());

	}

	@Test
	public void testUpdateIngredient() {
		Ingredient ingredient = new Ingredient("ham");
		UpdateIngredient updateIngredient = new UpdateIngredient("cheese");
		UpdateIngredientRequest payload = new UpdateIngredientRequest(updateIngredient);
		String resource = "/ingredients/" + ingredient.getId();
		String request = gson.toJson(payload);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request, headers);

		info("Creating ingredient...");

		repository.save(ingredient);

		ResponseEntity<Void> response =
				restTemplate.exchange(resource, HttpMethod.PUT, entity, Void.class);

		info("Updating ingredient...");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	public void testDeleteIngredient() {
		Ingredient ingredient = new Ingredient("ham");
		String resource = "/ingredients/" + ingredient.getId();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);


		info("Creating ingredients...");

		repository.save(ingredient);

		ResponseEntity<Void> response =
				restTemplate.exchange(resource, HttpMethod.DELETE, entity, Void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
