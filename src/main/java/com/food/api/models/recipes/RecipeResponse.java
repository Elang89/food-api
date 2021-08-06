package com.food.api.models.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecipeResponse {
	@Getter
	private Recipe recipe;

	public RecipeResponse(Recipe recipe) {
		this.recipe = recipe;
	}
}
