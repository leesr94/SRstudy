package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	// http://localhost:8088/doD
	// http://localhost:8088/doD?data=itwill
	@RequestMapping(value = "/doD", method = RequestMethod.GET)
//	public String doD(@ModelAttribute("data") String data) {
//	public String doD(Model model) {
	public String doD(RedirectAttributes rttr) {
		logger.debug("doD() 호출");
		
		// 데이터를 doE 주소(메서드)로 전달
		// 1. 주소줄에 파라미터 전달
//		logger.debug("data : " + data);
		
		// 2. Model 객체 생성
//		model.addAttribute("data", "ITWILL");
		
		// 3. redirect할 때만 쓸 수 있는 객체 RedirectAttributes -> Model과 똑같다.
//		rttr.addAttribute("data", "ITWILL2");
		rttr.addFlashAttribute("data", "ITWILL2");		// Flash : 일회성이라는 의미
		
//		return "/doE";
		return "redirect:/doE";		// -> 가상주소 변화 O, 뷰페이지 변경 O
//		return "forward:/doE";		// -> 가상주소 변화 X, 뷰페이지 변경 O
	}
	
	// http://localhost:8088/doE
	// http://localhost:8088/doE?data=itwill
	@RequestMapping(value = "/doE", method = RequestMethod.GET)
	public String doE(@ModelAttribute("data") String data) {
		logger.debug("doE() 호출");
		
		logger.debug("data : " + data);
		
		return "";
	}
}