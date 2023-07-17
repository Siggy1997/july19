package com.siggy.pro1;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

//inject사용해보기

@Repository("boardDAO")
public class BoardDAO {

	@Inject
	@Named("sqlSession")
	private SqlSession sqlsession;

	public List<Map<String, Object>> boardlist() {
		return sqlsession.selectList("board.boardList");
	}

	public BoardDTO detail(String bno) {

		return sqlsession.selectOne("board.detail", bno); //앞에는 네임스페이스.아이디, 값
	}
}
