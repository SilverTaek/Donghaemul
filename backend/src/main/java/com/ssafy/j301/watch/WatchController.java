package com.ssafy.j301.watch;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Watches", description = "조회 API")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/watches")
public class WatchController {

	private final WatchService watchService;

	@ApiOperation(value = "농축산물 조회 시 생성", notes = "농축산물 조회 테이블에 기록합니다.")
	@PostMapping
	public Watch insertWatch(@RequestBody Watch watch) {

		return watchService.insertWatch(watch);
	}

	@ApiOperation(value = "오늘 해당 농축산물을 조회한 유저 수", notes = "ingredientId로 오늘 해당 농축산물을 조회한 사람을 count합니다.")
	@GetMapping("/{ingredientId}")
	public int selectCount(@PathVariable Long ingredientId) {

		return watchService.selectCount(ingredientId);
	}

}
