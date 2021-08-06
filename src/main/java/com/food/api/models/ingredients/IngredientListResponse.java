package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
public class IngredientListResponse {

	@Getter
	private List<Ingredient> ingredients;

	public IngredientListResponse(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
