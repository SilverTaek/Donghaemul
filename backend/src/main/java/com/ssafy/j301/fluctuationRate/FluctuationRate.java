package com.ssafy.j301.fluctuationRate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FluctuationRate {

	private Long ingredientId;

	private String ingredientName;

	private String ingredientDetailName;

	private int ingredientAvgPrice;

	private double rate;

}
