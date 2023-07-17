package com.siggy.pro1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardService {

	@Inject
	@Named("boardDAO")
	private BoardDAO boardDAO;

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

		return boardDAO.detail(bno);
	}
}
