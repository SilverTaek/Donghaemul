package com.ssafy.j301.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestIngredientName {

	private Long ingredientId;

	private String ingredientName;

	private String ingredientDetailName;

}
