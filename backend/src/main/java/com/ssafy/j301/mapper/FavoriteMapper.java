package com.ssafy.j301.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.ssafy.j301.favorite.Favorite;

@Repository
public interface FavoriteMapper {

	public void insertFavorite(Favorite favorite);

	public void deleteFavorite(Favorite favorite);

	public int selectFavorite(@Param(value = "memberId") Long memberId,
			@Param(value = "ingredientId") Long ingredientId);

	public List<Favorite> selectAll(Long memberId);

}
