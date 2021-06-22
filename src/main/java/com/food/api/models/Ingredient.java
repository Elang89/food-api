package com.food.api.models;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Ingredient {

    @Getter
    private UUID id;

    @Getter
    private String name;
    
    public Ingredient(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
