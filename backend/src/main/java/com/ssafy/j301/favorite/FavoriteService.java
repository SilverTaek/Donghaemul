package com.ssafy.j301.favorite;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ssafy.j301.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {

	private final FavoriteMapper favoriteMapper;

	public void insertFavorite(Favorite favorite) {

		int checkFavorite = favoriteMapper.selectFavorite(favorite.getMemberId(), favorite.getIngredientId());
		if (checkFavorite == 0) {
			favoriteMapper.insertFavorite(favorite);
		} else {
			favoriteMapper.deleteFavorite(favorite);
		}
	}

	public List<Favorite> selectAll(Long memberId) {

		return favoriteMapper.selectAll(memberId);
	}

	public int selectFavorite(Long memberId, Long ingredientId) {

		return favoriteMapper.selectFavorite(memberId, ingredientId);
	}

}
