package com.onlyBoard.board.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlyBoard.board.service.BoardService;

@Controller
public class BoardController {

	Logger logger = Logger.getLogger(this.getClass());//로그

	@Autowired
	private BoardService boardService;//게시판 인터페이스
	
	/**
	 * 게시판 조회
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(HttpServletRequest request
			, @RequestParam(value="pageNum",defaultValue="1")int currentPage
			, @RequestParam(value="keyField", required =false) String keyField
			, @RequestParam(value="keyWord", required =false) String keyWord) {
		Map<String, Object> trsMap = new HashMap<String, Object>();
		trsMap.put("currentPage",currentPage);
		trsMap.put("keyField",keyField);
		trsMap.put("keyWord",keyWord);
		request.setAttribute("trsNo", getTrsNo());
		logger.info("============================== boardList START ===============================");
		logger.info("SESS_GUID = ["+request.getAttribute("trsNo")+"], [boardList] request param : "+trsMap.toString()); 
		ModelAndView  mav = new ModelAndView("board");	//board model 선언
		try {
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("currentPage", currentPage);
			reqMap.put("keyField"	, keyField);
			reqMap.put("keyWord"	, keyWord);
			Map<String, Object> map = boardService.selectPaging(request,reqMap);
			mav.setViewName("main/board");					
			mav.addObject("count"		, map.get("count"));
			mav.addObject("boardList"	, map.get("boardList"));
			mav.addObject("pagingHtml"	, map.get("pagingHtml"));
			mav.addObject("keyWord"		, map.get("keyWord"));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
 			logger.error(e.toString());
		}
		logger.info("============================= boardList E N D ===============================");
 		return mav;
	}
	
	/**
	 * 게시판 상세
	 * @param board_seq
	 * @return
	 */
	@RequestMapping(value="/boardDetail")
	public ModelAndView boardDetail(HttpServletRequest request) {
		logger.info("============================== boardDetail START ===============================");
		Map<String, Object> map = boardService.boardDetail(request);
		ModelAndView  mav = new ModelAndView("boardDetail");
		mav.setViewName("main/boardDetail");			//JSP 경로
		mav.addObject("boardList", map.get("boardList"));//게시판 리스트
		logger.info("============================== boardDetail E N D ===============================");
		return mav;
	}

	/**
	 * 게시판 업데이트
	 * @param board_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBoard")
	public String updateBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="board_title") String board_title
							, @RequestParam(value="board_content") String board_content
							, @RequestParam(value="user_name") String user_name) {
		logger.info("=============== updateBoard START ================");	
		String resultCode = "0000";//0000:정상 / 9000:에러	
		try {
			Map<String, Object> map = new HashMap<String, Object>();//게시글 업데이트 map
			map.put("board_seq", board_seq);//게시글 시퀀스
			map.put("board_title", board_title.replace("\r\n","<br>"));//게시글 제목
			map.put("board_content", board_content.replace("\r\n","<br>"));//게시글 내용
			map.put("user_name", user_name);//최종수정자	 
			resultCode = boardService.updateBoard(map);//게시글 업데이트	
		}catch(Exception e) {
			logger.error(e.toString());
			resultCode = "9000";		
		}//try		
		logger.info("end");
		logger.info("=============== updateBoard E N D ================");
		return resultCode;
	}
	
	/**
	 * 게시판 삭제
	 * @param board_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBoard")
	public String deleteBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="user_name") String user_name) {
		logger.info("=============== deleteBoard START ================");
		String resultCode = "0000";//0000:정상 / 9000:에러
		try {		
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("board_seq", board_seq);//게시글 시퀀스
			map.put("user_name", user_name);//최종수정자		
			resultCode = boardService.deleteBoard(map);//게시글 삭제			
		}catch(Exception e) {
			logger.error(e.toString());
			resultCode = "9000";
		}//try
		logger.info("=============== deleteBoard E N D ================");
		return resultCode;
	}
	
	/**
	 * 게시글 작성 페이지 이동
	 * @return
	 */
	@RequestMapping(value="/writeBoard")
	public ModelAndView boardWrite() {
				
		ModelAndView  mav = new ModelAndView("boardWrite");
		mav.setViewName("main/boardWrite");//jsp 경로
		
		return mav;
	}
	
	/**
	 * 게시글 작성
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertBoard")
	public String boardInsert(@RequestParam(value="board_title", required =false) String board_title
							  , @RequestParam(value="board_content", required =false) String board_content
							  , @RequestParam(value="user_name", required =false) String user_name) {	
		String resultCode = "0000";//0000:정상 / 9000:에러	
		try {	
			Map<String, Object> map = new HashMap<String, Object>();//게시글 삭제 map
			map.put("board_title", board_title);//게시글 제목
			map.put("board_content", board_content);//게시글 내용
			map.put("created_by", user_name);//생성자
			map.put("last_update_by", user_name);//최종수정자
			resultCode = boardService.insertBoard(map);//게시글 삭제		
		}catch(Exception e) {
			logger.error(e.toString());
			resultCode = "9000";		
		}//try			
		logger.info("end");	
		return resultCode;
	}
	
	/**
	 * 트랜잭션 번호 생성
	 * @return
	 */
	public static synchronized String getTrsNo() {
		Random rand 			= new Random();
		SimpleDateFormat sdf 	= new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Calendar cal 			= Calendar.getInstance();	
		Date date 				= cal.getTime();
		int rnum 				= rand.nextInt(100);
		String rnumStr 			= String.valueOf(rnum);
		int len 				= rnumStr.length();
		for (int i = 0; i < 2 - len; i++) {
			rnumStr = "0" + rnumStr;
		}
		String trsNo 			= "9999" + sdf.format(date) + rnumStr;
		return trsNo;
	}
}
