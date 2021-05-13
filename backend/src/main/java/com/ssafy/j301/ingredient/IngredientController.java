package com.ssafy.j301.ingredient;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Ingredients", description = "농축산물 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;

	@ApiOperation(value = "비로그인 시 메인페이지 조회", notes = "즐겨찾기 포함 전체 농축산물 조회")
	@GetMapping
	public List<Ingredient> selectAll() {

		return ingredientService.selectAll();
	}

	@ApiOperation(value = "로그인 시 메인페이지 조회", notes = "조회 수 포함 전체 농축산물 조회")
	@GetMapping("/{memberId}")
	public List<Ingredient> loginSelectAll(@PathVariable Long memberId) {

		return ingredientService.selectAllByLogin(memberId);
	}

	@ApiOperation(value = "농축산물 ID로 조회", notes = "IngredientId로 조회한 결과를 리턴합니다.")
	@GetMapping("/detailId/{ingredientId}")
	public Ingredient selectByIngredientId(@PathVariable Long ingredientId) {

		return ingredientService.selectByingredientId(ingredientId);
	}

	@ApiOperation(value = "농축산물 이름으로 조회", notes = "IngredientName으로 조회한 결과를 리턴합니다.")
	@GetMapping("/detailName/{ingredientName}")
	public Ingredient selectByIngredientName(@PathVariable String ingredientName) {

		return ingredientService.selectByIngredientName(ingredientName);
	}

	@ApiOperation(value = "농축산물 날짜기준 항목 이름 조회", notes = "IngredientId, IngredientName을 조회합니다.")
	@GetMapping("/ingredientName")
	public List<RequestIngredientName> selectAllName() {

		return ingredientService.selectAllName();
	}

}
