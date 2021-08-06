package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateIngredient {

	@Getter
	private String name;

	public UpdateIngredient(String name) {
		this.name = name;
	}
}
