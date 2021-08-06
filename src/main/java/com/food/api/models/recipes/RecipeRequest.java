package com.food.api.models.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecipeRequest {

	@Getter
	private Recipe recipe;

	public RecipeRequest(Recipe recipe) {
		this.recipe = recipe;
	}
}
