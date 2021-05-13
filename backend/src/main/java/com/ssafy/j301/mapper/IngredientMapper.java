package com.ssafy.j301.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ssafy.j301.ingredient.Ingredient;
import com.ssafy.j301.ingredient.RequestIngredientName;

@Repository
public interface IngredientMapper {

	public List<Ingredient> selectAll();

	public List<Ingredient> selectAllByLogin(Long memberId);

	public Ingredient selectByIngredientId(Long ingredientId);

	public Ingredient selectByIngredientName(String ingredientName);

	public List<RequestIngredientName> selectAllName();

}
