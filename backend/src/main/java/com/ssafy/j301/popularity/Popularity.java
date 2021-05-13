package com.ssafy.j301.popularity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Popularity {

	private Long ingredientId;

	private String ingredientName;

	private String ingredientDetailName;

	private int popularity;

}
