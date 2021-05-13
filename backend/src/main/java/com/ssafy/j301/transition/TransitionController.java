package com.ssafy.j301.transition;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.j301.mapper.TransitionMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Transition", description = "1년간 가격 동향 그래프 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/transition")
public class TransitionController {

	private final TransitionMapper transitionMapper;

	@ApiOperation(value = "1년간 농축산물 가격 동향 그래프", notes = "현재 날짜 기준 1년간 현재가격, 예측가격, 날짜를 리턴합니다.")
	@GetMapping("/{ingredientId}")
	public List<Transition> insertWatch(@PathVariable Long ingredientId) {

		return transitionMapper.selectTransition(ingredientId);
	}

}
