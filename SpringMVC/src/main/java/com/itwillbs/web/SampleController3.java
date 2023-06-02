package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	// http://localhost:8088/doC
	// http://localhost:8088/doC?msg=hello
	
	@RequestMapping(value = "/doC", method = RequestMethod.GET)
	public String doC(String msg) {
		logger.debug("doC() 호출");
		
		// 파라미터 값이 있는 경우 -> 매개변수 활용
		logger.debug("msg : " + msg);
		
		return "doC";
	}
	
//	@RequestMapping(value = "/doC", method = RequestMethod.GET)
//	public String doC(@ModelAttribute("msg") String tmp) {	// -> 앞에 modelatrribute를 붙이면 매개변수 명이 달라도 가능
//															// -> 저장한 데이터를 자동으로 뷰페이지에서 el 표현식으로 사용 가능 (param.안해도)
////	public String doC(String tmp) {
//		logger.debug("doC() 호출");
//		
//		// 파라미터 값이 있는 경우 -> 매개변수 활용
//		logger.debug("msg : " + tmp);
//		
//		return "doC";
//	}
	
	
	// 파라미터가 1개 이상일 경우
	// http://localhost:8088/doC1?msg=hello&age=20
	
//	@RequestMapping(value = "/doC1", method = RequestMethod.GET)
//	public String doC1(@ModelAttribute("msg") String tmp, @ModelAttribute("age") int age) {
//		logger.debug("doC1() 호출");
//		
//		// 파라미터 값이 있는 경우 -> 매개변수 활용
//		logger.debug("msg : " + tmp);
//		logger.debug("age : " + age);
//		
//		return "doC";
//	}
	
	// http://localhost:8088/doC2?userid=admin&userpw=1234
	// http://localhost:8088/doC2?userid=admin&userpw=1234&tel=010-1234-5678
	
//	@RequestMapping(value = "/doC2", method = RequestMethod.GET)
////	public String doC2(@ModelAttribute("userid") String userid, @ModelAttribute("userpw") String userpw) {
//	public String doC2(MemberVO vo, @ModelAttribute("tel") String tel) {
//		// -> 해당 메서드는 주소줄을 통해 호출되는 시스템이다. 그렇기 때문에, 주소줄에서 객체 내에 값이 없는 doC2를 부르면 내용x,
//		//	  주소줄에서 값이 있는 doC를 부르면 해당 메서드가 실행된다.
//		//    vo 객체에 없는 값을 받고 싶으면, MemberVO vo 뒤에 [ , @ModelAttribute("tel") String tel ]을 추가해주면 된다.
//		
//		logger.debug("doC2() 호출");
//		
//		// 파라미터 값이 있는 경우 -> 매개변수 활용
//		logger.debug("userid : " + vo.getUserid());
//		logger.debug("userpw : " + vo.getUserpw());
//		logger.debug("tel : " + tel);
//		
//		return "doC";
//	}
	
	
	// 파라미터가 아닌 정보를 뷰페이지로 전달 -> DB에서 만든 것
	// http://localhost:8088/doC3
	
	@RequestMapping(value = "/doC3", method = RequestMethod.GET)
	public String doC3(Model model) {
		logger.debug("doC3() 호출 - 파라미터가 아닌 정보를 뷰 페이지로 전달");
		
		// DB 데이터 (임시로 만듦)
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		
		// 영역에 저장 (jsp에서 request.setAttribute 한 것) 불가능! -> 영역의 역할을 대신하는 Model 객체 사용!!
		// Model : Spring에서 제공하는 컨테이너(박스). 컨트롤러와 뷰 사이에서 정보를 옮겨주는 역할을 한다.
		model.addAttribute("vo", vo);
		
		return "doC";
	}
	
}