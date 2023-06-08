package com.itwillbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링(root-context.xml)에서 해당 객체를 서비스 객체로 인식하도록 설정

@Service
public class MemberServiceImpl implements MemberService {
	
	// DAO와 Controller를 연결
	// DAO 객체에 대한 접근 필요 -> 의존 관계 (의존 주입)
	@Inject
	private MemberDAO mdao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		mdao.insertMember(vo);
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		return mdao.loginMember(vo);
	}
	
}