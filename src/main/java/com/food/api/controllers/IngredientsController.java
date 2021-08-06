package com.food.api.controllers;

import java.util.UUID;
import com.food.api.models.ingredients.Ingredient;
import com.food.api.models.ingredients.IngredientListResponse;
import com.food.api.models.ingredients.IngredientRequest;
import com.food.api.models.ingredients.IngredientResponse;
import com.food.api.models.ingredients.UpdateIngredient;
import com.food.api.models.ingredients.UpdateIngredientRequest;
import com.food.api.services.IngredientsRepository;

import static com.food.api.constants.CommonConstants.DEFAULT_QUERY_LIMIT;

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
@RequestMapping("/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientsRepository repository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IngredientListResponse> getAllIngredients(
			@RequestParam(value = "limit", defaultValue = DEFAULT_QUERY_LIMIT) int limit) {

		var response = new IngredientListResponse(repository.findAll());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IngredientResponse> getOneIngredient(@PathVariable("id") UUID id) {

		var response = new IngredientResponse(repository.findById(id).get());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IngredientResponse> createIngredient(
			@RequestBody IngredientRequest request) {

		Ingredient ingredient = request.getIngredient();
		repository.save(ingredient);

		var response = new IngredientResponse(ingredient);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateIngredient(@PathVariable("id") UUID id,
			@RequestBody UpdateIngredientRequest request) {

		UpdateIngredient updateIngredient = request.getUpdateIngredient();
		Ingredient ingredient = repository.findById(id).get();

		ingredient.setName(updateIngredient.getName());

		repository.save(ingredient);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteIngredient(@PathVariable("id") UUID id) {
		repository.deleteById(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
