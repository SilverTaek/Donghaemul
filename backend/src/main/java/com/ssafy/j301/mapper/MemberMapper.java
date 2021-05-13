package com.ssafy.j301.mapper;

import org.springframework.stereotype.Repository;
import com.ssafy.j301.member.Member;

@Repository
public interface MemberMapper {

	public void insertMember(Member member);

	public boolean checkEmail(String memberEmail);

	public Member getMemberByMemberEmailAndPassword(Member member);

	public Member getMemberByMemberEmail(Member member);

}
