package com.ssafy.j301.shopping;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shopping {

	private Long shoppingApiId;

	private Long ingredientId;

	private int shoppingApiPrice;

	@NonNull
	private String shoppingApiTitle;

	@NonNull
	private String shoppingApiStore;

	@NonNull
	private LocalDate shoppingApiDate;

	@NonNull
	private String shoppingApiLink;

}
