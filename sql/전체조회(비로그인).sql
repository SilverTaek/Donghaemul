		SELECT 
        
        ingredient.ingredient_id,
        ingredient.ingredient_name,
        ingredient.ingredient_category,
        ingredient_avg.ingredient_avg_id,
        ingredient_avg.ingredient_avg_price,
        ingredient_avg.ingredient_avg_date,
        ingredient_avg.ingredient_avg_predict_price,
        (
			SELECT count(*)
			FROM watch
			WHERE ingredient.ingredient_id = watch.ingredient_id
		) AS "count"
        
        FROM ingredient LEFT OUTER JOIN ingredient_avg ON ingredient.ingredient_id = ingredient_avg.ingredient_id, watch
		
        WHERE ingredient_avg.ingredient_avg_date = "2019-01-01" AND
        watch.watch_date = "2019-01-01"
        
        GROUP BY ingredient.ingredient_id
        
        ORDER BY (
			SELECT count(*)
			FROM watch
			WHERE ingredient.ingredient_id = watch.ingredient_id
		) DESC;
        