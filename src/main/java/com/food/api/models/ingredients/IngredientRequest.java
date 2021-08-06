package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IngredientRequest {

	@Getter
	private Ingredient ingredient;


	public IngredientRequest(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
