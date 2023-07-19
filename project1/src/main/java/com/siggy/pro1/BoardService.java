package com.siggy.pro1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardService {

	@Inject
	@Named("boardDAO")
	private BoardDAO boardDAO;
	
	@Autowired
	private Util util;

	// 보드 리스트 불러오는 메소드
	public List<Map<String, Object>> boardList() {
		return boardDAO.boardlist();

		/*
		 * List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		 * 
		 * for (int i = 1; i < 11; i++) { Map<String,Object> map = new HashMap<String,
		 * Object>(); map.put("bno", i); map.put("btitle", i+ "번째 글입니다");
		 * map.put("bwrite", "홍길동"); map.put("bdate", "2023-07-17"); map.put("blike",
		 * i*10); list.add(map);
		 * 
		 * } return list;
		 */

	}

	public BoardDTO detail(String bno) {
		BoardDTO dto = boardDAO.detail(bno);
		
		
		if (dto.getBip() !=null && dto.getBip().indexOf(".") != -1 ) {
			
		String[] arrbip = dto.getBip().split("\\.");
		arrbip[1] = "★";
		dto.setBip(String.join(".", arrbip));
		}
		return dto;
	}

	public void write(BoardDTO dto) {
		//btitle은 꺼내줍니다
		String btitle = dto.getBtitle();
		//값을 변경하겠습니다. replace() < = &lt   > = &gt
		//a = a.replace("<", "&lt");
		//다시 저장해주세요
		//dto.setBtitle(a);
		btitle = util.exchange(btitle);
		dto.setBtitle(btitle);
		
		//select를 제외한 나머지는 영향받은 행의 수 (int)를 받아오기도 합니다
		boardDAO.write(dto);
	}

	public void delete(BoardDTO dto) {
		boardDAO.delete(dto);
	}
}
