package com.onlyBoard.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.onlyBoard.board.model.BoardVO;

/**
 * 게시판 인터페이스(조회, 생성, 수정, 삭제)
 *
 */
public interface BoardService {

	/**
	 * 게시글 리스트 수
	 * @param map
	 * @return
	 */
	public int selectBoardCnt(Map<String, Object> map);
	
	/**
	 * 게시글 리스트
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map);
	
	/**
	 * 게시글 상세
	 */
	public BoardVO selectBoard(int board_seq);
	
	/**
	 * 게시글 업데이트
	 */
	public String updateBoard(Map<String, Object> map);
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public String deleteBoard(Map<String, Object> map);
	
	/**
	 * 게시글 작성
	 */
	public String insertBoard(Map<String, Object> map);
	
	/**
	 * 페이징 처리
	 * @param request
	 * @return
	 */
	public Map<String, Object> selectPaging(HttpServletRequest request);
	
	/**
	 * 게시판 상세조회
	 * @param request
	 * @return
	 */
	public Map<String, Object> boardDetail(HttpServletRequest request);
}
