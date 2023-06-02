package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller		// -> 컨트롤러의 역할 수행을 위한 어노테이션 (servlet-context.xml에 객체 등록을 하는 과정)
public class SampleController1 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController1.class);
	
	// http://localhost:8088/프로젝트명/ -> 기존 model2방식
	// http://localhost:8088/top-level 패키지명/ -> springMVC 방식 (com.itwillbs.web의 web만 해당)
	
	// 현재 컨트롤러의 시작 주소 -> http://localhost:8088/web/
	
	// 현재 컨트롤러의 시작 주소 -> http://localhost:8088/web/doA
	
	// 컨트롤러에서 필요한 동작을 메서드로 작성
//	@RequestMapping(value = "매핑할 주소", method = 전달방식) : value -> 가상주소 비교 및 처리 동작, method -> get/post 방식
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public void doA() {
		logger.debug("doA() 실행");
	}
	// -> 메서드의 리턴타입이 void라면? /WEB-INF/views/[주소].jsp 로 자동 이동!
	
	// doA1 주소로 호출되는 메서드 doA1() 실행
	// http://localhost:8088/web/doA1
//	@RequestMapping("/doA1")			// get, post 방식 언급하지 않아도 이동 자체는 된다.
	@GetMapping(value = "/doA1")		// get 방식으로 매핑하겠다는 어노테이션
	public void doA1() {
		logger.debug("doA1() 실행");
	}
	
}