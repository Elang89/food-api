package com.food.api.models.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IngredientResponse {

    @Getter
    private Ingredient ingredient;


    public IngredientResponse(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
