package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	// 회원가입 처리 - 정보 입력
	
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
	// 회원가입 처리 - 정보 처리
	
	
	// http://localhost:8088/member/login
	// 로그인 - 정보 입력 (get)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.debug("loginGET() 호출");
		logger.debug("/member/login.jsp 뷰페이지 연결(자동)");
	}
	// 로그인 - 정보 입력 (get)
	
	// 로그인 - 정보 처리 (post)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session) {
	// 또는 @ModelAtrribute("userid") String userid 해도 되지만, vo로 한번에 가능하니까 안함!
		logger.debug("loginPOST() 호출");
		
		// 전달 정보 (로그인할 id, pw 저장)
		logger.debug("vo : " + vo);
		
		// 서비스 -> DAO 로그인 체크 -> 로그인 여부에 따라 페이지 이동
		MemberVO resultVO = mService.memberLogin(vo);
		logger.debug("resultVO : " + resultVO);
		
		if (resultVO != null) {		// - 로그인 성공 : 메인페이지 이동 (redirect) + 로그인 아이디를 세션에 저장
			// session은 현재 Spring에서 사용할 수가 없다. 하지만, login.jsp에서는 사용 가능! why? jsp는 내장객체가 있기 때문.
			// -> 그래서 login.jsp에서 넘어오는 loginPOST는 jsp에서 넘어오기 때문에, session을 받아올 수가 있다.
			session.setAttribute("id", resultVO.getUserid());
			
			logger.debug("로그인 성공");
			return "redirect:/member/main";		// 주소를 바꾸면서 메인 페이지로 이동
		} else {					// - 로그인 실패 : 다시 로그인 페이지
			logger.debug("로그인 실패");
			return "redirect:/member/login";	// 주소를 바꾸면서 메인 페이지로 이동
		}
	}
	// 로그인 - 정보 처리 (post)
	
	// http://localhost:8088/member/main
	// 메인 페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET() {
		logger.debug("mainGET() 호출");
		logger.debug("/member/main.jsp 뷰페이지 이동(자동)");
	}
	// 메인 페이지
	
	
	// http://localhost:8088/member/logout
	// 로그아웃 -> 로그아웃 동작이 끝나면 다른 페이지로 이동해야하므로, 리턴타입을 명시하면 이동 가능
	// -> 주소를 명시해서 이동하기 때문에 get방식! (onclick으로 이동하니까)
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.debug("logoutGET() 호출");
		
		// 세션 정보 초기화
		session.invalidate();
		
		return "redirect:/member/login";
	}
	// 로그아웃
	
	
	// http://localhost:8088/member/info
	// 회원정보 조회
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void memebrInfoGET(HttpSession session, Model model) {
		logger.debug("memberInfoGET() 호출");
		
		// 회원정보 가져오기 - 세션에 저장된 아이디 정보 -> DB에 있는 해당하는 회원정보 전부 조회
		// 서비스 -> DAO
		String id = (String) session.getAttribute("id");
		MemberVO resultVO = mService.getMember(id);
		logger.debug("resultVO : " + resultVO);
		
		// 연결된 뷰페이지에 전달 -> Model 객체 사용
//		model.addAttribute("resultVO", resultVO);
		model.addAttribute(resultVO);
		
		// 페이지 이동 -> void 타입으로 지정했기 때문에 return 없어도 자동으로 이동
		logger.debug("/member/info.jsp 뷰페이지로 이동(자동)");
	}
	// 회원정보 조회
	
	
	// http://localhost:8088/member/modify
	// 회원정보 수정 - 기존의 정보 출력 + 수정정보 입력
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@SessionAttribute String id, Model model) {
		logger.debug("modifyGET() 호출");
		logger.debug("id : " + id);
		
		// 기존의 회원정보 화면 출력 (수정 가능한 정보는 이름, 이메일 - 수정 조건은 id/pw가 동일)
		MemberVO resultVO = mService.getMember(id);
		
		// Model 객체 사용하여 정보 저장 후 전달
		model.addAttribute("resultVO", resultVO);
		
		// 페이지 이동
		logger.debug("/member/modify 뷰페이지 이동(자동)");
	}
	// 회원정보 수정
	
	// 회원정보 수정 처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO uvo) {
		logger.debug("modifyPOST() 호출");
		// 한글처리 -> web.xml에 필터로 처리했기 때문에 여기서 생략
		
		// 전달정보 저장 (수정데이터)
		logger.debug("(파라미터 자동 수집)uvo : " + uvo);
		
		// 서비스 -> DAO - 회원정보수정 메서드
		Integer result = mService.memberModify(uvo);
		logger.debug("result(수정 결과) : " + result);
		
		// 페이지 이동
		return "redirect:/member/main";
	}
	// 회원정보 수정 처리
	
	
	// http://localhost:8088/member/remove
	// 회원정보 삭제
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public void removeGET(@SessionAttribute String id, Model model) {
		logger.debug("removeGET() 호출");
		logger.debug("id : " + id);
		
		// 삭제할 정보 출력 - 비밀번호 일치 시에만 삭제
		MemberVO resultVO = mService.getMember(id);
		
		// 삭제할 회원의 비밀번호 전달
		model.addAttribute("resultVO", resultVO);
		
		// 페이지 이동
		logger.debug("/member/remove 뷰페이지 이동(자동)");
	}
	// 회원정보 삭제
	
	// 회원정보 삭제 처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(HttpSession session, @SessionAttribute String id, @ModelAttribute("userpw") String pw) {
		logger.debug("removePOST() 호출");
		
		// 삭제할 정보 저장 (세션에 있는 id, 전달받은 pw)
		logger.debug("id : " + id + "pw : " + pw);
		MemberVO dvo = new MemberVO();
		dvo.setUserid(id);
		dvo.setUserpw(pw);
		
		// 서비스 -> DAO 삭제 메서드 호출
		Integer result = mService.memberRemove(dvo);
		logger.debug("result : " + result);
		
		if (result == 1) {		// 삭제 성공 시 세션 만료
			session.invalidate();
		}
		
		return "redirect:/member/main";
	}
	// 회원정보 삭제 처리
	
	
	// http://localhost:8088/member/list
	// 회원목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(Model model) {
		logger.debug("listGET() 호출");
		
		// 서비스 -> DAO 회원목록 출력 메서드
		List<MemberVO> memberList = mService.getMemberList();
		
		// Model 객체에 저장
		model.addAttribute("memberList", memberList);
		
		logger.debug("/member/list 뷰페이지로 이동(자동)");
	}
	// 회원목록

}