SELECT ingredient.ingredient_id, ingredient.ingredient_name, round((ingredient_avg_price - ingredient_avg_previous_price)/ ingredient_avg_previous_price * 100, 2) AS rate
FROM ingredient, ingredient_avg
WHERE NOT ingredient_avg_previous_price IS NULL && ingredient_avg_date = '2019-01-02' && ingredient.ingredient_id = ingredient_avg.ingredient_id
ORDER BY rate
LIMIT 5;
