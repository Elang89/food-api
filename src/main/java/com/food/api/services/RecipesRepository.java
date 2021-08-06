package com.food.api.services;

import java.util.UUID;
import com.food.api.models.recipes.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipesRepository extends CrudRepository<Recipe, UUID> {

}
