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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.food.api.models.ingredients.Ingredient;
import com.food.api.models.recipes.Recipe;
import com.food.api.models.recipes.RecipeListResponse;
import com.food.api.models.recipes.RecipeRequest;
import com.food.api.models.recipes.RecipeResponse;
import com.food.api.models.recipes.UpdateRecipe;
import com.food.api.models.recipes.UpdateRecipeRequest;
import com.food.api.services.IngredientsRepository;
import com.food.api.services.RecipesRepository;
import com.google.gson.Gson;


public class RecipesControllerTest extends AbstractIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	Gson gson;

	@Autowired
	RecipesRepository recipesRepository;
	@Autowired
	IngredientsRepository ingredientsRepository;

	@Before
	public void clear() {
		recipesRepository.deleteAll();
		ingredientsRepository.deleteAll();
	}

	@Test
	public void testFindAllRecipes() {
		info("Getting all recipes...");

		String resource = "/recipes";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<RecipeListResponse> response =
				restTemplate.exchange(resource, HttpMethod.GET, entity, RecipeListResponse.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getRecipes().size()).isBetween(0, 200);

	}

	@Test
	public void testFindOneRecipeById() {
		Set<Ingredient> ingredients = createIngredients();
		Recipe recipe = new Recipe("Ham Sandwich", ingredients);
		String resource = "/recipes/" + recipe.getId();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		info("Creating recipe...");


		recipesRepository.save(recipe);


		info("Retrieving recipe...");

		ResponseEntity<RecipeResponse> response =
				restTemplate.exchange(resource, HttpMethod.GET, entity, RecipeResponse.class);

		Recipe retrievedRecipe = response.getBody().getRecipe();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retrievedRecipe.getId()).isEqualTo(recipe.getId());
	}

	@Test
	public void testCreateRecipe() {
		Set<Ingredient> ingredients = createIngredients();
		Recipe recipe = new Recipe("Ham Sandwich", ingredients);
		RecipeRequest payload = new RecipeRequest(recipe);
		String resource = "/recipes";
		String request = gson.toJson(payload);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request, headers);

		info("Creating Recipe...");

		ResponseEntity<RecipeResponse> response =
				restTemplate.exchange(resource, HttpMethod.POST, entity, RecipeResponse.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getRecipe().getId()).isEqualTo(recipe.getId());

	}

	@Test
	public void testUpdateRecipe() {
		Set<Ingredient> ingredients = createIngredients();
		Recipe recipe = new Recipe("Ham Sandwich", ingredients);

		ingredients.removeIf(x -> x.getName() == "ham");

		UpdateRecipe updateRecipe = new UpdateRecipe("Cheese Sandwich", ingredients);
		UpdateRecipeRequest payload = new UpdateRecipeRequest(updateRecipe);
		String resource = "/recipes/" + recipe.getId();
		String request = gson.toJson(payload);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request, headers);

		info("Creating ingredient...");

		recipesRepository.save(recipe);

		ResponseEntity<Void> response =
				restTemplate.exchange(resource, HttpMethod.PUT, entity, Void.class);

		info("Updating ingredient...");

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	public void testDeleteRecipe() {
		Set<Ingredient> ingredients = createIngredients();
		Recipe recipe = new Recipe("Ham Sandwich", ingredients);
		String resource = "/recipes/" + recipe.getId();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);


		info("Creating recipe...");

		recipesRepository.save(recipe);

		ResponseEntity<Void> response =
				restTemplate.exchange(resource, HttpMethod.DELETE, entity, Void.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}


	public Set<Ingredient> createIngredients() {
		var ingredients = new HashSet<Ingredient>(Arrays.asList(new Ingredient("ham"),
				new Ingredient("tomato"), new Ingredient("lettuce"), new Ingredient("cheese"),
				new Ingredient("mustard"), new Ingredient("bread")));

		ingredientsRepository.saveAll(ingredients);

		return ingredients;
	}
}
