package com.food.api.models.recipes;

import java.util.Set;
import com.food.api.models.ingredients.Ingredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateRecipe {

	@Getter
	private String name;
	@Getter
	private Set<Ingredient> ingredients;

	public UpdateRecipe(String name, Set<Ingredient> ingredients) {
		this.name = name;
		this.ingredients = ingredients;
	}


}
