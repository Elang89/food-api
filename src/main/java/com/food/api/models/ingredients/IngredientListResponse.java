package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
public class IngredientListResponse {

	@Getter
	private Set<Ingredient> ingredients;

	public IngredientListResponse(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
