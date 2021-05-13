package com.ssafy.j301.ingredientAvg;

import org.springframework.stereotype.Service;
import com.ssafy.j301.mapper.IngredientAvgMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientAvgService {

	private final IngredientAvgMapper ingredientAvgMapper;

	public double selectRate(Long ingredientId) {

		IngredientAvg avg = ingredientAvgMapper.selectRate(ingredientId);
		double FluctuationRate = 0;

		if (avg.getIngredientAvgPrice() != 0 && avg.getIngredientAvgPreviousPrice() != 0)
			FluctuationRate = ((double) avg.getIngredientAvgPrice() - (double) avg.getIngredientAvgPreviousPrice())
					/ (double) avg.getIngredientAvgPreviousPrice() * 100;

		return FluctuationRate;
	}

	public IngredientAvg selectPrice(Long ingredientId) {

		return ingredientAvgMapper.selectPrice(ingredientId);
	}

	public int selectIntervalPrice(Long ingredientId) {

		return ingredientAvgMapper.selectIntervalPrice(ingredientId);
	}

}
