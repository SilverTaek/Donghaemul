package com.ssafy.j301.popularity;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.j301.mapper.PopularityMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Popularity", description = "인기도 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/popularity")
public class PopularityController {

	private final PopularityMapper populartiyMapper;

	@ApiOperation(value = "Best Top5 조회", notes = "상위 인기도 5개를 조회합니다.")
	@GetMapping
	public List<Popularity> selectPopularity() {
		return populartiyMapper.selectPopularity();
	}

}
