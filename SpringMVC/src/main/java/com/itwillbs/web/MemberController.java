package com.itwillbs.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

/* <필요한 작업>
 * 1. 컨트롤러별 공통 주소(URI) 설계
 * 2. 각 기능별 주소(URI) 설계
 * 3. 각 URI별 호출 방식 설정 (get / post) -> 사용자의 정보 입력 or 조회는 get 방식, 데이터 처리 or DB 접근 필요는 post 방식
 * 4. 결과 처리, 페이지 이동 설계
 * 5. 예외 처리
 */

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스에 대한 정보 필요 -> 의존 관계 (의존 주입)
	@Inject
	private MemberService mService;
	
	// 동작 구현 -> 메서드로 설계
	
	// http://localhost:8088/member/MemberJoin.me
	// http://localhost:8088/member/join
	// 회원가입 처리 - 정보 입력
//	@RequestMapping(value = "/MemberJoin.me", method = RequestMethod.GET)		// 기존 매핑 방식
	@RequestMapping(value = "/join", method = RequestMethod.GET)				// void를 String으로 바꾸고 매핑 방식 바꾸기
//	public void memberJoin() {
	public String memberJoinGET() {
		logger.debug("memberJoinGET() 호출");
		logger.debug("/member/MemberJoin.jsp 뷰페이지 연결(자동)");
		
		return "/member/MemberJoin";
	}
	
	// 회원가입 처리 - 정보 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memberJoinPOST(HttpServletRequest request, MemberVO vo) throws Exception {
		// 매개변수 선언 = 반드시 그 값을 받을 것 = 관련 정보(파라미터 get이든 post든)가 있다면 자동 수집
		// => 한글처리 하기도 전에 깨진 값이 그대로 받아들어 온다.
		
		logger.debug("memberJoinPOST() 호출");
		
		// 한글 처리 (post 방식으로 받기 때문에) => 아래의 코드는 더이상 작동x web.xml에 한글필터 처리 했음!
//		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장 (회원가입할 정보를 파라미터로 받아서 저장)
		logger.debug("vo : " + vo);
		
		// 서비스를 통해서 회원가입 메서드 호출 -> DAO의 회원가입 메서드 호출
		// 30번코드에서 서비스 의존 주입함!
		mService.memberJoin(vo);
		
		// 페이지 이동 -> 로그인 페이지
		return "redirect:/member/login";		// 주소를 바꾸면서 로그인 페이지로 이동
	}
	
	
	// 로그인 - 정보 입력
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.debug("loginGET() 호출");
		logger.debug("/member/login.jsp 뷰페이지 연결(자동)");
	}
	
	// 로그인 - 정보 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo) {		// 또는 @ModelAtrribute("userid") String userid 해도 되지만, vo로 한번에 가능하니까 안함!
		logger.debug("loginPOST() 호출");
		
		// 전달 정보 (로그인할 id, pw 저장)
		logger.debug("vo : " + vo);
		
		// 서비스 -> DAO 로그인 체크 -> 로그인 여부에 따라 페이지 이동
		MemberVO resultVO = mService.memberLogin(vo);
		logger.debug("resultVO : " + resultVO);
		// - 로그인 성공 : 메인페이지 이동 (redirect) + 로그인 아이디를 세션에 저장
		
		// - 로그인 실패 : 다시 로그인 페이지
		
		return "redirect:/member/main";		// 주소를 바꾸면서 메인 페이지로 이동
	}
	
}