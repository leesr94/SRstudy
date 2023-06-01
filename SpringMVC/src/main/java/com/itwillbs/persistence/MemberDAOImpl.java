package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링(root-context.xml)에 해당 파일이 DAO의 동작을 하는 객체라고 등록하는 것

@Repository
public class MemberDAOImpl implements MemberDAO {
	// DB 연결										=> SqlSessionFactory 객체 주입
	// SQL 작성 & pstmt -> (물음표 있다면 채우기)
	// SQL 실행 -> (select구문이라면 데이터 처리)
	
	@Inject
	private SqlSession sqlSession;
//	private SqlSessionFactory sqlFactory;
	// -> 이 코드만으로 Test에서는 가능하지만, 여기는 Test가 아니므로 당장 객체 주입X (가져올 곳을 찾지X)
	// -> 클래스에 @Repository를 해줘야 객체 주입이 된다!
	
	// 네임스페이스 (매퍼 위치를 상수로 미리 작성)
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	// 로거 작성 (단축키 등록한 걸로)
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Override
	public String getTime() {
		String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		// -> memberMapper.xml의 namespace 복사 + . + 해당 id, 리턴은 string으로 받겠다고 정의했기 때문에 리턴은 String이 된다.
		
		System.out.println("DB 시간 정보 : " + time);
		
		return time;
	}

	@Override
	public void insertMember(MemberVO vo) {
		// DB 연결 -> sqlSession으로 처리 완료됨
		// SQL 작성 & pstmt -> mapper에서 완료
		// SQL 실행
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		logger.debug("회원가입 완료!");
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		logger.debug("Test -> DAO 호출 : 로그인 동작 수행");
		
		// SQL 구문 작성 & 실행
		logger.debug("DAO -> mapper 사용 -> SQL 실행");
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".login", vo);		// vo에 id와 pw만 담아서 매퍼로 전달
		logger.debug("SQL 실행결과 리턴 -> 테스트");
//		logger.debug("리턴값 : " + resultVO.toString());	// -> null값일 때 toString 사용 불가능!!!
		logger.debug("리턴값 : " + resultVO + "");
		
		return resultVO;
	}

	@Override
	public MemberVO loginMember(String id, String pw) {
		// 아이디, 비밀번호 전달 받아서 사용(1개 이상의 정보를 mapper에 각각 전달 불가능) -> vo로 한 번에 보낸다.
//		MemberVO vo = new MemberVO();
//		vo.setUserid(userid);
//		vo.setUserpw(userpw);
		// -> 이렇게 해도 되지만, 처음부터 파라미터를 객체 단위(vo)로 전달하면 편하다.
		
		// cf) 만약, 하나의 객체에 한 번에 담을 수 없는 정보라면? -> 컬렉션프레임워크인 Map 사용
				// ex) 특정 유저가 쓴 글을 업데이트할 경우(관계 없는 것들의 join 결과를) 어떻게 한 번에 가져올 것인지?
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("mapper 매핑된 이름", 실제로 전달되는 값);
		params.put("userid", id);
		params.put("userpw", pw);
		
		// SQL 호출 & 실행
		sqlSession.selectOne(NAMESPACE + ".login", params);
		
		return null;
	}

	@Override
	public Integer updateMember(MemberVO uvo) {
		logger.debug("테스트 -> DAO 호출 : 회원정보 수정");
		// 수정할 정보 가져오기 (uvo)
		
		logger.debug("DAO -> mapper 호출 -> SQL 실행");
		Integer result = sqlSession.update(NAMESPACE + ".update", uvo);
		
		logger.debug("SQL 실행 결과 리턴");
		
		return result;
	}

	@Override
	public Integer deleteMember(MemberVO dvo) {
		logger.debug("테스트 -> DAO 호출 : 회원정보 삭제");
		// 삭제할 정보 가져오기 (dvo)
		
		logger.debug("DAO -> mapper 호출 -> SQL 실행");
		Integer result = sqlSession.delete(NAMESPACE + ".delete", dvo);
		
		logger.debug("SQL 실행 결과 리턴");
		
		return result;
	}
	
}