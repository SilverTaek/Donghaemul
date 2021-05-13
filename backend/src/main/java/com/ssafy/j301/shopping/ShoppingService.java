package com.ssafy.j301.shopping;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ssafy.j301.mapper.ShoppingMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

	private final ShoppingMapper shoppingMapper;

	public List<Shopping> selectShopping(Long ingredientId) {

		return shoppingMapper.selectShopping(ingredientId);
	}

}
