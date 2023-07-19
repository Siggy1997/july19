package com.siggy.pro1;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
	//user -> Controller -> Service -> DAO -> mybatis -> DB
	
	//AutoWired 말고 resource로 연결zz
	@Resource(name = "boardService")
	//자바가 이름으로 연결해줍니다
	private BoardService boardService;
	
	
	
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
		
		//상대방 ip가져오기 2023/07-19 
		String ip = null;
	      
	      ip = request.getHeader("X-Forwarded-For");

	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("Proxy-Client-IP");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("WL-Proxy-Client-IP");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("HTTP_CLIENT_IP");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("X-Real-IP");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("X-RealIP");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getHeader("REMOTE_ADDR");
	      }
	      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	         ip = request.getRemoteAddr();
	      }
		
		BoardDTO dto = new BoardDTO();
		dto.setBtitle(request.getParameter("title"));
		dto.setBcontent(ip + request.getParameter("content"));
		dto.setBwrite("Siggy");
		
		boardService.write(dto);
		
		return "redirect:board";
	}
	
	
}
