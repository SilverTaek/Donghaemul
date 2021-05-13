package com.ssafy.j301.mapper;

import org.springframework.stereotype.Repository;
import com.ssafy.j301.ingredientAvg.IngredientAvg;

@Repository
public interface IngredientAvgMapper {

	public IngredientAvg selectRate(Long ingredientId);

	public IngredientAvg selectPrice(Long ingredientId);

	public int selectIntervalPrice(Long ingredientId);

}
