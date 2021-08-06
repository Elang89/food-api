package com.food.api.services;

import java.util.Set;
import java.util.Optional;
import java.util.UUID;
import com.food.api.models.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipesRepository extends CrudRepository<Recipe, UUID> {
	Set<Recipe> findAll();

	Optional<Recipe> findById(UUID id);

}
