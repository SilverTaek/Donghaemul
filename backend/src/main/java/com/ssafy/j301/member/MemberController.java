package com.ssafy.j301.member;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Members", description = "사용자  API")
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/members")
public class MemberController {

	private final MemberService memberService;

	@ApiOperation(value = "가입하기", notes = "중복 이메일, 이름을 검사합니다.")
	@PostMapping("/signup")
	public Member signup(@RequestBody Member member) {

		return memberService.signup(member);
	}

	@ApiOperation(value = "로그인", notes = "로그인 성공 시 토큰을 반환합니다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member member) {

		return memberService.login(member);
	}

	@ApiOperation(value = "소셜 로그인", notes = "로그인 성공 시 토큰을 반환합니다.")
	@PostMapping("/social")
	public ResponseEntity<Map<String, Object>> social(@RequestBody Member member) {

		return memberService.social(member);
	}
}
