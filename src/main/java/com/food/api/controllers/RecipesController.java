package com.food.api.controllers;

import com.food.api.services.RecipesRepository;
import com.food.api.models.recipes.Recipe;
import com.food.api.models.recipes.RecipeListResponse;
import com.food.api.models.recipes.RecipeRequest;
import com.food.api.models.recipes.RecipeResponse;
import com.food.api.models.recipes.UpdateRecipe;
import com.food.api.models.recipes.UpdateRecipeRequest;

import static com.food.api.constants.CommonConstants.DEFAULT_QUERY_LIMIT;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

	@Autowired
	private RecipesRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeListResponse> getAllRecipes(
			@RequestParam(value = "limit", defaultValue = DEFAULT_QUERY_LIMIT) int limit) {

		var response = new RecipeListResponse(repository.findAll());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeResponse> getOneRecipe(@PathVariable("id") UUID id) {
		var response = new RecipeResponse(repository.findById(id).get());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
		Recipe recipe = request.getRecipe();
		repository.save(recipe);

		var response = new RecipeResponse(recipe);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateIngredient(@PathVariable("id") UUID id,
			@RequestBody UpdateRecipeRequest request) {

		UpdateRecipe updateRecipe = request.getUpdateRecipe();
		Recipe recipe = repository.findById(id).get();

		recipe.setName(updateRecipe.getName());
		recipe.setIngredients(updateRecipe.getIngredients());

		repository.save(recipe);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteIngredient(@PathVariable("id") UUID id) {
		repository.deleteById(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

