package com.siggy.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	//AutoWired 말고 resource로 연결zz
	@Resource(name = "boardService")
	//자바가 이름으로 연결해줍니다
	private BoardService boardService;
	
	
	@Autowired
	private Util util;
	
	
	@GetMapping("/board")
	public String board(Model model) {
		//서비스에서 값 가져오기
		// boardService.boardList(); //한줄로 줄이기
		model.addAttribute("list", boardService.boardList());
		return "board";
	}
	//http://localhost:8080/pro1/detail?bno=105
	//파라미터로 들어오는값 잡기
	@GetMapping("/detail")  //model은 jsp에 값을 붙이기 위해서 넣었습니다
	public String detail(HttpServletRequest request, Model model) {
		String bno = request.getParameter("bno");
		//bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		//System.out.println("bno" + bno);
		BoardDTO dto = boardService.detail(bno);
		model.addAttribute("dto", dto);
		
		return "detail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
	@PostMapping("/write")
	public String write(HttpServletRequest request) {
		//사용자가 입력한 데이터 변수에 담기
		//Service -> DAO -> mybatis -> DB로 보내서 저장하기
		//System.out.println(request.getParameter("title"));
		//System.out.println(request.getParameter("content"));
		

		BoardDTO dto = new BoardDTO();
		dto.setBtitle(request.getParameter("title"));
		dto.setBcontent(util.getIp() + request.getParameter("content"));
		dto.setBip(util.getIp()); //얻어온 ip도 저장해서 데이터 베이스로 보내겠습니다
		dto.setBwrite("Siggy");
		
		boardService.write(dto);
		
		return "redirect:board";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "bno", required = false, defaultValue = "0") int bno) { 
		//HttpServletRequest의 getParameter();합친거
		
		//dto
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		//추후 로그인 하면 사용자의 정보도 담아서 보냅니다
		
		boardService.delete(dto);
		System.out.println("bno : " + bno);
		return "redirect:board";
	}
	
	
}
