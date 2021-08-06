package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateIngredientRequest {

	@Getter
	private UpdateIngredient updateIngredient;

	public UpdateIngredientRequest(UpdateIngredient updateIngredient) {
		this.updateIngredient = updateIngredient;
	}
}
