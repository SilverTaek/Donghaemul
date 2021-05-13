package com.ssafy.j301.shopping;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "Shoppings", description = "쇼핑 API")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/shoppings")
public class ShoppingController {

	private final ShoppingService shoppingService;

	@GetMapping("/{ingredientId}")
	public List<Shopping> selectShopping(@PathVariable Long ingredientId) {

		return shoppingService.selectShopping(ingredientId);
	}

}
