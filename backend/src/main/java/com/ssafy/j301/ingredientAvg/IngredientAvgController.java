package com.ssafy.j301.ingredientAvg;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "IngredientAvg", description = "농축산물 가격 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/ingredientAvg")
public class IngredientAvgController {

	private final IngredientAvgService ingredientAvgService;

	@ApiOperation(value = "오늘 날짜 기준 농축산물 등락률 조회", notes = "(현재가 - 전일가)/전일가 * 100")
	@GetMapping("/rate/{ingredientId}")
	public double selectRate(@PathVariable Long ingredientId) {

		return ingredientAvgService.selectRate(ingredientId);
	}

	@ApiOperation(value = "오늘 날짜 기준 농축산물 현재가격 조회", notes = "농축산물 오늘 현재 가격 조회")
	@GetMapping("/price/{ingredientId}")
	public IngredientAvg selectPrice(@PathVariable Long ingredientId) {

		return ingredientAvgService.selectPrice(ingredientId);
	}

	@ApiOperation(value = "오늘 날짜 기준 농축산물 등락가 조회", notes = "농축산물 오늘 현재 등락가 조회")
	@GetMapping("/price/interval/{ingredientId}")
	public int selectIntervalPrice(@PathVariable Long ingredientId) {

		return ingredientAvgService.selectIntervalPrice(ingredientId);
	}

}
