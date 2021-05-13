        SELECT 
		
		ingredient.ingredient_id,
		ingredient.ingredient_name,
		ingredient.ingredient_category,
		ingredient_avg.ingredient_avg_id,
		ingredient_avg.ingredient_avg_price,
		ingredient_avg.ingredient_avg_date,
		ingredient_avg.ingredient_avg_predict_price,
		CASE WHEN ingredient.ingredient_id in (
			SELECT ingredient_id
			FROM favorite
			WHERE member_id = "1"
		) THEN 0 ELSE 1 END AS "favorite",
        (
			SELECT count(*)
			FROM watch
			WHERE ingredient.ingredient_id = watch.ingredient_id
		) AS "count"
				
		FROM ingredient LEFT OUTER JOIN ingredient_avg ON ingredient.ingredient_id = ingredient_avg.ingredient_id, watch, favorite

		WHERE ingredient_avg.ingredient_avg_date = "2019-01-01" AND
		watch.watch_date = "2019-01-01"
				
		GROUP BY ingredient.ingredient_id
				
		ORDER BY CASE WHEN ingredient.ingredient_id in (
			SELECT ingredient_id
			FROM favorite
			WHERE member_id = "1"
		) THEN 0 ELSE 1 END, (
			SELECT count(*)
			FROM watch
			WHERE ingredient.ingredient_id = watch.ingredient_id
		) DESC;
