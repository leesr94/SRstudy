package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest {
	// Controller + Action을 합친 Test
	// 생성 해둔 기능 호출
	
	// 로거 (syso를 대신해서 사용)
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	// DAO 객체 의존 주입
	@Inject
	private MemberDAO mdao;
	
//	@Inject
//	private SqlSession sqlSession;
	// -> 해당 코드를 안 써도 된다. why? DAOImpl.class에 가면 SqlSession을 부르고 있기 때문에 굳이 여기서 사용 안해도 된다!!
	
	@Test
	public void DB시간정보_조회() throws Exception {
//		System.out.println("DB 시간 정보(daoTest) : " + mdao.getTime());
		logger.info(mdao.getTime());
	}
	
//	@Test
	public void 로그레벨테스트() throws Exception {
		logger.warn("warn 레벨 실행");
		logger.info("info 레벨 실행");
		logger.debug("debug 레벨 실행");
	}
	
//	@Test
	public void 회원가입테스트() throws Exception {
		logger.debug("뷰에서 정보를 입력 받아서 생성");
		
		MemberVO vo = new MemberVO();
		vo.setUserid("user005");
		vo.setUserpw("1234");
		vo.setUsername("사용자five");
		vo.setUseremail("user005@user.com");
		
		logger.debug("DAO - 회원가입 메서드 호출");
		// DAO 객체 주입
		mdao.insertMember(vo);
	}
	
//	@Test
	public void 로그인테스트() throws Exception {
		logger.debug("로그인 테스트 시작");
		
		// 로그인 계정
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("12");
		
		// DAO - 로그인 체크하는 메서드 호출
		MemberVO resultVO = mdao.loginMember(vo);
		
		// 정보에 따른 제어
		if (resultVO == null) {		// 로그인 정보 없음
			logger.debug("로그인 실패");
		} else {					// 로그인 정보 있음
			logger.debug("로그인 성공");
		}
		
		logger.debug("로그인 테스트 종료");
	}
	
//	@Test
	public void 회원정보수정테스트() throws Exception {
		logger.debug("정보수정 테스트 시작@@@@@@@@@@@@@@@");
		
		// 수정할 회원 정보
		MemberVO uvo = new MemberVO();
		uvo.setUserid("itwill");
		uvo.setUserpw("12");
		uvo.setUsername("사용자수정");
		uvo.setUseremail("itwill(수정)@itwill.com");
		
		// DAO - 정보수정 메서드 호출
		Integer result = mdao.updateMember(uvo);
		
		// 정보에 따른 제어
		if (result == 1) {		// 로그인 성공
			logger.debug("정상 수정 완료");
		} else {				// 로그인 실패 (result == 0)
			logger.debug("수정 실패 (아이디 or 비밀번호 오류)");
		}
		
		logger.debug("정보수정 테스트 종료@@@@@@@@@@@@@@@");
	}
	
	@Test
	public void 회원정보삭제테스트() throws Exception {
		logger.debug("회원 삭제 테스트 시작$$$$$$$$$$$$$$$");
		
		// 삭제할 회원 정보
		MemberVO dvo = new MemberVO();
		dvo.setUserid("itwill");
		dvo.setUserpw("1234");
		
		// DAO - 회원 삭제 메서드 호출
		Integer result = mdao.deleteMember(dvo);
		
		// 정보에 따른 제어
		if (result == 1) {		// 삭제할 정보 있음
			logger.debug("정상 삭제 완료");
		} else {				// 삭제할 정보 없음
			logger.debug("삭제 실패 (아이디 or 비밀번호 오류)");
		}
		
		logger.debug("회원 삭제 테스트 종료$$$$$$$$$$$$$$$");
	}
	
}