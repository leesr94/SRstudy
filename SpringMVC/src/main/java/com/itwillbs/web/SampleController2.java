package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/itwill")		// -> 컨트롤러별로 시작되는 도메인 (즉, view 안에 있는 폴더)을 설정
public class SampleController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	// http://localhost:8088/itwill/doB
	
//	@RequestMapping(value = "/doB", method = RequestMethod.GET)
//	public void doB() {
//		logger.debug("doB() 호출");
//		logger.debug("연결된 뷰페이지로 이동 (컨트롤러에서 자동으로 처리)");
//	}
	
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	public String doB() {
		logger.debug("doB() 호출");
		logger.debug("연결된 뷰페이지로 이동 (컨트롤러에서 자동으로 처리)");
		
		return "/itwill/string";
	}
	
	// http://localhost:8088/itwill/doB1
	
	@RequestMapping(value = "/doB1", method = RequestMethod.GET)
	public String doB1() {
		logger.debug("doB1() 호출");
		logger.debug("연결된 뷰페이지로 이동 (컨트롤러에서 자동으로 처리)");
		
		return "string";
	}
	
}