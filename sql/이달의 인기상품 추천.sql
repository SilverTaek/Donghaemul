SELECT ingredient.ingredient_id, ingredient.ingredient_name, sum(fame.fav) as fame_score
FROM ingredient join ((
                        SELECT ingredient_id, (COUNT(*)*3) AS fav
                        FROM favorite
                        GROUP BY ingredient_id
                      )
                      UNION
                      (
                        SELECT ingredient_id, COUNT(*) AS fav
                        FROM watch
                        WHERE MONTH(watch.watch_date) = MONTH(current_date())
                        GROUP BY ingredient_id
                      )) AS fame
ON ingredient.ingredient_id = fame.ingredient_id
GROUP BY fame.ingredient_id
ORDER BY SUM(fame.fav) DESC
LIMIT 5;