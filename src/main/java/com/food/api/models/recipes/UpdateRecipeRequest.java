package com.food.api.models.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateRecipeRequest {
	@Getter
	private UpdateRecipe updateRecipe;


	public UpdateRecipeRequest(UpdateRecipe updateRecipe) {
		this.updateRecipe = updateRecipe;
	}
}

