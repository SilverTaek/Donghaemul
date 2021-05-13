package com.ssafy.j301.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ssafy.j301.transition.Transition;

@Repository
public interface TransitionMapper {

	public List<Transition> selectTransition(Long ingredientId);

}
