package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 구현동작 설계
	
	// 회원가입
	public void memberJoin(MemberVO vo);
	
	// 로그인 체크
	public MemberVO memberLogin(MemberVO vo);
}