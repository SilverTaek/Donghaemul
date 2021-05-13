package com.ssafy.j301.ingredient;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ssafy.j301.fluctuationRate.FluctuationRate;
import com.ssafy.j301.mapper.FluctuationRateMapper;
import com.ssafy.j301.mapper.IngredientMapper;
import com.ssafy.j301.mapper.PopularityMapper;
import com.ssafy.j301.popularity.Popularity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

	private final IngredientMapper ingredientMapper;
	private final FluctuationRateMapper fluctuationRateMapper;
	private final PopularityMapper popularityMapper;

	public List<Ingredient> selectAll() {

		List<Ingredient> list = ingredientMapper.selectAll();

		return insertStatus(list);
	}

	public List<Ingredient> selectAllByLogin(Long memberId) {

		List<Ingredient> list = ingredientMapper.selectAllByLogin(memberId);

		return insertStatus(list);
	}

	public Ingredient selectByingredientId(Long ingredientId) {

		return ingredientMapper.selectByIngredientId(ingredientId);
	}

	public Ingredient selectByIngredientName(String ingredientName) {

		return ingredientMapper.selectByIngredientName(ingredientName);
	}

	public List<RequestIngredientName> selectAllName() {

		return ingredientMapper.selectAllName();
	}

	public List<Ingredient> insertStatus(List<Ingredient> list) {

		List<Popularity> bestList = popularityMapper.selectPopularity();

		List<FluctuationRate> warningList = fluctuationRateMapper.selectRateOfRise();

		for (int i = 0; i < bestList.size(); i++) {
			long bestId = bestList.get(i).getIngredientId();
			for (int j = 0; j < list.size(); j++)
				if (list.get(j).getIngredientId() == bestId)
					list.get(j).setStatus(1);
		}

		for (int i = 0; i < warningList.size(); i++) {
			long warningId = warningList.get(i).getIngredientId();
			for (int j = 0; j < list.size(); j++)
				if (list.get(j).getIngredientId() == warningId)
					list.get(j).setStatus(2);
		}

		return list;
	}

}
