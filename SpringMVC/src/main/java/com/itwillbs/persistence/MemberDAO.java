package com.itwillbs.persistence;

import com.itwillbs.domain.MemberVO;

// persistence : 영속성 -> DB 관련 처리

public interface MemberDAO {
	// 필요한 동작들을 추상메서드로 사용하여 정의만
	
	// DB 시간 정보 조회 추상메서드
	public String getTime();
	
	// CRUD 게시판
	// Create - 회원정보 가입
	public void insertMember(MemberVO vo);
	
	// Read - 로그인 (회원정보 조회)
	public MemberVO loginMember(MemberVO vo);
	
	// Read - 로그인 (회원정보 조회) 다른 .ver
	public MemberVO loginMember(String id, String pw);
	
	// Update - 회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	// Delete - 회원정보 삭제
	public Integer deleteMember(MemberVO dvo);
}