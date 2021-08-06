CREATE TABLE IF NOT EXISTS recipes_ingredients (
    recipe_id uuid REFERENCES recipes (id) ON UPDATE CASCADE ON DELETE CASCADE,
    ingredient_id uuid REFERENCES ingredients (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT recipes_ingredients_pkey PRIMARY KEY (recipe_id, ingredient_id))
