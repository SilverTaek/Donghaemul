SELECT ingredient_id, ingredient_avg_date, ingredient_avg_price, ingredient_avg_predict_price
FROM ingredient_avg
WHERE ingredient_id = 1 AND DATEDIFF(SYSDATE(), ingredient_avg_date) <= 365
ORDER BY ingredient_avg_date;
