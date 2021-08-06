package com.food.api.services;

import java.util.Set;
import java.util.Optional;
import java.util.UUID;
import com.food.api.models.ingredients.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository<Ingredient, UUID> {

	Set<Ingredient> findAll();

	Optional<Ingredient> findById(UUID id);

}
