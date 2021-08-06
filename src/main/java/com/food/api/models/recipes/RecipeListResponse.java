package com.food.api.models.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
public class RecipeListResponse {

	@Getter
	private Set<Recipe> recipes;

	public RecipeListResponse(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
}
