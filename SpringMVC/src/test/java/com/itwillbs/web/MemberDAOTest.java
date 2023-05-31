package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
//	@Test
	public void DB시간정보_조회() throws Exception {
//		System.out.println("DB 시간 정보(daoTest) : " + mdao.getTime());
		logger.info(mdao.getTime());
	}
	
	@Test
	public void 로그레벨테스트() throws Exception {
		logger.warn("warn 레벨 실행");
		logger.info("info 레벨 실행");
		logger.debug("debug 레벨 실행");
	}
}