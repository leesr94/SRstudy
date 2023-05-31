package com.itwillbs.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MysqlConnectTest {
	/*
	 * 일반적으로 클래스는 main() 없이 코드 실행 불가능.
	 * -> Junit을 사용해서 main() 또는 서버 없이 코드를 실행한다. (테스트 개발 이라고 한다.)
	 */
	
	// Java로 DB 연결 (JDBC)
	// 상수 선언
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/springdb";
	private static final String DBID = "root";
	private static final String DBPW = "1234";
	
	
	// DB 연결 메서드
//	@Test
//	public void testDBConnection() throws Exception {
//		System.out.println("DB 연결 테스트 - 시작");
//		
//		// 1. 드라이브 로드
//		Class.forName(DRIVER);
//		
//		// 2. DB 연결수행
//		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
//		
//		System.out.println("DB 연결 성공!");
//		System.out.println("con : " + con);
//		
//		System.out.println("DB 연결 테스트 - 종료");
//	}
	
	
	// DB 연결 메서드
	@Test
	public void testDBConnection() {
		System.out.println("DB 연결 테스트 - 시작");
		
		// try ~ with 구문 : finally 구문의 자원해제를 대신 구현하는 형태의 예외처리 문법
		try (Connection con = DriverManager.getConnection(DBURL, DBID, DBPW)) {
			// 1. 드라이브 로드
			Class.forName(DRIVER);
			
			// 2. DB 연결수행
//			Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
			// 해당 코드를 try에 삽입하여 try~with구문을 통해 자동으로 자원 해제 처리할 수 있게 만든다.
			
			System.out.println("DB 연결 성공!");
			System.out.println("con : " + con);
		} catch (Exception e) {
			e.printStackTrace();
		} //finally {
			// 자원해제 코드
		//}
		
		System.out.println("DB 연결 테스트 - 종료");
	}
}