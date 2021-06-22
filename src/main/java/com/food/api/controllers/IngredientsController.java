package com.food.api.controllers;

import java.util.List;

import com.food.api.constants.CommonConstants;
import com.food.api.models.Ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
public class IngredientsController {

    @GetMapping(path="/ingredients")
    public List<Ingredient> get_all_ingredients(
        @RequestParam(defaultValue = CommonConstants.DEFAULT_QUERY_LIMIT) String limit,
        @RequestParam(defaultValue = CommonConstants.DEFAULT_QUERY_OFFSET) String offset
    ) {
        return List.of(new Ingredient("ham"), new Ingredient("cheese"));
    }

    @GetMapping(path="/ingredients/{id}")
    public Ingredient get_ingredient(@PathVariable("id") String id) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(path="/ingredients")
    public Ingredient create_ingredient(Ingredient ingredient) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping(path="/ingredients/{id}")
    public Ingredient update_ingredient(@PathVariable("id") String id, Ingredient ingredient) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path="/ingredients/{id}")
    public Ingredient delete_ingredient(@PathVariable("id") String id, Ingredient ingredient) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);  
    }
}
