package com.siggy.pro1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//첫화면 로딩 :index.jsp호풀
@Controller
public class IndexController {

	@GetMapping(value = {"/", "/index"})
	public String index() {
		return"index"; //데이터 붙임 없이 index.jsp페이지만 보여줍니다
	}
	
}
