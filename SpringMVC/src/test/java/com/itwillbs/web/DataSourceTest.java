package com.itwillbs.web;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class) -> Spring, JUnit4를 사용하여 테스트함을 선언
//@ContextConfiguration(locations = { "file:src/main/WEB-INF/spring/root-context.xml" })
//	-> 프로젝트(파일) 실행에 필요한 설정 정보의 위치 선언

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {
	
	// DataSourceTest는 DB 연결정보(객체)가 필요
	// -> DataSourceTest는 DB 연결정보(객체)에 의존적(의존관계 o)
	// -> 객체 생성(x), 의존관계 주입(o)
	
//	private DataSource ds = new DataSource();  -> 인터페이스므로 직접적인 객체 생성x
	
	// 객체 의존관계 주입 (DI)
//	@Inject -> 스프링이 생성해놓은 객체를 자동으로 주입
//	@Inject
	@Autowired
	private DataSource ds;
	
	// DB 연결 테스트
	@Test
	public void testConnect() throws Exception {
		System.out.println("연결객체 : " + ds);
		
		Connection con = ds.getConnection();
		
		System.out.println("DB 연결 성공");
		System.out.println("con : " + con);
	}
}