package com.onlyBoard.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.onlyBoard.board.model.BoardVO;

/**
 * 게시판 DAO 정의
 *
 */
public class BoardDAO extends SqlSessionDaoSupport {
	
	/**
	 * 게시글 리스트 수
	 */
	public int selectBoardCnt(Map<String, Object> map) throws Exception {
		return getSqlSession().selectOne("selectBoardCnt", map);
	}
	
	/**
	 * 게시글 리스트
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) throws Exception {
		return getSqlSession().selectList("selectBoardList", map);
	}
	
	/**
	 * 게시글 상세
	 */
	public BoardVO selectBoard(int board_seq) throws Exception { 
		return (BoardVO) getSqlSession().selectOne("selectBoard", board_seq);
	} 

	/**
	 * 게시글 업데이트
	 */
	public void updateBoard(Map<String, Object> map) throws Exception {
		getSqlSession().update("updateBoard", map);
	}
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public void deleteBoard(Map<String, Object> map) throws Exception {
		getSqlSession().update("deleteBoard", map);
	}
	
	/**
	 * 게시글 생성
	 */
	public void insertBoard(Map<String, Object> map) throws Exception {
		getSqlSession().insert("insertBoard", map);
	}
}
  