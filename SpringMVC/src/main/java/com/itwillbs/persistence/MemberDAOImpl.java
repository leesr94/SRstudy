package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

// @Repository : 스프링(root-context.xml)에 해당 파일이 DAO의 동작을 하는 객체라고 등록하는 것

@Repository
public class MemberDAOImpl implements MemberDAO {
	// DB 연결										=> SqlSessionFactory 객체 주입
	// SQL 작성 & pstmt -> (물음표 있다면 채우기)
	// SQL 실행 -> (select구문이라면 데이터 처리)
	
	@Inject
	private SqlSessionFactory sqlFactory;
	// -> 이 코드만으로 Test에서는 가능하지만, 여기는 Test가 아니므로 당장 객체 주입X (가져올 곳을 찾지X)
	// -> 클래스에 @Repository를 해줘야 객체 주입이 된다!
	
	@Override
	public String getTime() {
		
		return null;
	}
	
}