package com.ssafy.j301.mapper;

import org.springframework.stereotype.Repository;
import com.ssafy.j301.watch.Watch;

@Repository
public interface WatchMapper {

	public void insertWatch(Watch watch);

	public int selectCount(Long ingredientId);

}
