package com.ssafy.j301.mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ssafy.j301.shopping.Shopping;

@Repository
public interface ShoppingMapper {

	public List<Shopping> selectShopping(Long ingredientId);

}
