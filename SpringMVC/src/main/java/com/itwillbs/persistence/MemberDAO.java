package com.itwillbs.persistence;

// persistence : 영속성 -> DB 관련 처리

public interface MemberDAO {
	// 필요한 동작들을 추상메서드로 사용하여 정의만
	
	// DB 시간 정보 조회 추상메서드
	public String getTime();
}