package com.ssafy.j301.favorite;

import java.util.List;
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

@Api(tags = "Favorites", description = "즐겨찾기 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/favorites")
public class FavoriteController {

	private final FavoriteService favoriteService;

	@ApiOperation(value = "즐겨찾기 추가", notes = "memberId, ingredientId로 추가/이미 즐겨찾기했으면 삭제")
	@PostMapping
	public void insertFavorite(@RequestBody Favorite favorite) {

		favoriteService.insertFavorite(favorite);
	}

	@ApiOperation(value = "사용자 즐겨찾기 전체 조회", notes = "memberId로 즐겨찾기 항목 전체 조회")
	@GetMapping("/{memberId}")
	public List<Favorite> selectAll(@PathVariable Long memberId) {

		return favoriteService.selectAll(memberId);
	}

	@ApiOperation(value = "사용자 즐겨찾기 체크", notes = "즐겨찾기 항목 조회")
	@GetMapping("/{memberId}/{ingredientId}")
	public int selectFavorite(@PathVariable Long memberId, @PathVariable Long ingredientId) {

		return favoriteService.selectFavorite(memberId, ingredientId);
	}

}
