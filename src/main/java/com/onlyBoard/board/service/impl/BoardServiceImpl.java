package com.onlyBoard.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlyBoard.board.dao.BoardDAO;
import com.onlyBoard.board.model.BoardVO;
import com.onlyBoard.board.service.BoardService;
import com.onlyBoard.board.util.PagingUtil;
import com.onlyBoard.util.Utility;

/**
 * 게시판 ServiceImpl 정의
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	Logger logger = Logger.getLogger(this.getClass());//로그
	
	@Autowired
	private BoardDAO boardDAO;//게시판 DAO
	
	/* 
	 * 게시글 리스트 수 조회	
	 */
	public int selectBoardCnt(Map<String, Object> map) {	
		int boardCnt = 0;
		try {
			boardCnt = boardDAO.selectBoardCnt(map);
		}
		catch (Exception e) {
			logger.error("selectBoardCnt()");
			logger.error(e.toString());	
		}
		return boardCnt;
	}
	
	/**
	 * 게시글 리스트
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//게시판VO List
		
		try {
			
			boardList = boardDAO.selectBoardList(map);//게시판 리스트 조회
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * 게시글 상세
	 */
	public BoardVO selectBoard(int board_seq) {
		
		BoardVO boardVO = new BoardVO();//게시판VO
		
		try {
			
			boardVO = boardDAO.selectBoard(board_seq);//게시판 상세조회
			
		}catch(Exception e) {
			
			logger.error("selectBoard()");
			logger.error(e.toString());
			
		}//try
		
		return boardVO;
	
	}
	
	/**
	 * 게시글 업데이트
	 */
	public String updateBoard(Map<String, Object> map) {

		String resultCode = "0000";// 0000:정상 / 9000:에러
		
		try {

			boardDAO.updateBoard(map);//게시글 업데이트
			resultCode = "0000";// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
			
		}
		
		return resultCode;
	}
	
	/**
	 * 게시글 삭제 (사용여부만 변경 1 -> 0) 
	 * 1:사용중 / 0:미사용  
	 */
	public String deleteBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:정상 / 9000:에러
		
		try {

			boardDAO.deleteBoard(map);//게시글 업데이트
			resultCode = "0000";// 0000:정상 / 9000:에러
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
			
		}//try
		
		return resultCode;
	}
	
	public String insertBoard(Map<String, Object> map) {
		String resultCode = "0000";// 0000:정상 / 9000:에러
		try {
			boardDAO.insertBoard(map);//게시글 생성
			resultCode = "0000";// 0000:정상 / 9000:에러
		}catch(Exception e) {
			logger.error(e.toString());
			resultCode = "9000";// 0000:정상 / 9000:에러
		}//try
		return resultCode;
	}
	
	/* 
	 * 페이징처리
	 */
	public Map<String, Object> selectPaging(HttpServletRequest request) {
		return null;
//		Map<String, Object> map = getDefaultPaingMap(request); 
//		String keyField	= Utility.convertNull((String)map.get("keyField"));
//		String keyWord	= Utility.convertNull((String)map.get("keyWord"));
// 		int currentPage = (Integer) map.get("currentPage");
//		int count = selectBoardCnt(map);	 //게시판 리스트 수 조회
//		PagingUtil page;					//페이징 처리를 위한 객체 선언
//		if (null == keyWord) {//검색어가 있다면
//			page = new PagingUtil(currentPage, count, 5,2, "boardList.do");							
//		}
//		else {				  //검색어가 없다면
//			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "boardList.do",null);	
//		}
//		
//		map.put("start", page.getStartCount());	//시작 게시물번호
//		map.put("end", page.getEndCount());		//마지막게시물번호
//
//		//---------------------------
//		//게시물이 1개 이상 존재하면 리스트 조회
//		//---------------------------
//		List<BoardVO> boardList = new ArrayList<BoardVO>();		//게시판 리스트
//		if (0 < count ) {
//			boardList = selectBoardList(map);//게시판 리스트 조회
//		};//if
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("count", count);						//총레코드수
//		resultMap.put("boardList", boardList);				//게시판 리스트
//		resultMap.put("pagingHtml", page.getPagingHtml());	//링크문자열을 전달
//		resultMap.put("keyWord", keyWord);					//검색어 전달
//		return resultMap;
	}
	
	/**
	 * 게시판 상세 조회
	 * @param request 
	 */
	public Map<String, Object> boardDetail(HttpServletRequest request) {
		
		String seqStr = (String)request.getParameter("board_seq");
		if ("".equals(seqStr)) return null;
		int boardSeq = Integer.parseInt(request.getParameter("board_seq"));
		BoardVO boardList = selectBoard(boardSeq);//게시판 상세 조회
		boardList.getBoard_seq();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", boardList);
		return map;
	}
	
	/**
	 * 게시판 페이징 Map
	 * @param request
	 * @return
	 */
	public Map<String, Object>getDefaultPaingMap(HttpServletRequest request){
		int currentPage = 1;//현재 페이지
		String pageStr	= Utility.convertNull((String)request.getParameter("pageNum"));	//스트링으로 유효성 체크
		String keyField	= Utility.convertNull((String)request.getParameter("keyField"));	//검색구분
		String keyWord	= Utility.convertNull((String)request.getParameter("keyWord"));	//검색어
		if (!"".equals(pageStr)) currentPage = Integer.parseInt(pageStr);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyField", keyField);		//검색분야
		map.put("keyWord", keyWord);		//검색어
		map.put("currentPage", currentPage);//현재 페이지
		return map;
	}
	
}
