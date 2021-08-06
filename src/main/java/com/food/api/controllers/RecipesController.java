package com.food.api.controllers;

import com.food.api.services.RecipesRepository;
import com.food.api.models.recipes.RecipeListResponse;
import com.food.api.models.recipes.RecipeRequest;
import com.food.api.models.recipes.RecipeResponse;
import com.food.api.models.recipes.UpdateRecipeRequest;

import static com.food.api.constants.CommonConstants.DEFAULT_QUERY_LIMIT;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

	@Autowired
	private RecipesRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeListResponse> getAllRecipes(
			@RequestParam(value = "limit", defaultValue = DEFAULT_QUERY_LIMIT) int limit) {

		throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeResponse> getOneRecipe(@PathVariable("id") UUID id) {
		throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
		throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateIngredient(@PathVariable("id") UUID id,
			@RequestBody UpdateRecipeRequest request) {

		throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
	}
}

