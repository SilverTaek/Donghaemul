package com.ssafy.j301.ingredient;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientAvg {

	private Long ingredientId;

	private LocalDate ingredientAvgDate;

	private int ingredientAvgPrice;

	private int ingredientAvgPredictPrice;

}
