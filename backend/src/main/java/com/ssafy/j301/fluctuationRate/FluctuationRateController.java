package com.ssafy.j301.fluctuationRate;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.j301.mapper.FluctuationRateMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "FluctuationRate", description = "Warning 농축산물 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/fluctuationRates")
public class FluctuationRateController {

	private final FluctuationRateMapper fluctuationRateMapper;

	@ApiOperation(value = "등락률 Top5 조회", notes = "등락율 내림차순으로 5개 리스트업")
	@GetMapping("/rate/{ingredientId}")
	public List<FluctuationRate> selectfluctuationRates() {

		return fluctuationRateMapper.selectRateOfDecline();
	}

}
