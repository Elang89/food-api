package com.food.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.food.api.models.ingredients.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository<Ingredient, UUID> {

	List<Ingredient> findAll();

	Optional<Ingredient> findById(UUID id);

	Ingredient findByName(String name);

}
