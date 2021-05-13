package com.ssafy.j301.watch;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Watch {

	private Long watchId;

	@NonNull
	private Long memberId;

	@NonNull
	private Long ingredientId;

	@NonNull
	private LocalDate watchDate;

}
