package com.onlyBoard.board.controller;

import java.util.HashMap;
import java.util.Map;

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

	Logger logger = Logger.getLogger(this.getClass());//�α�

	@Autowired
	private BoardService boardService;//�Խ��� �������̽�
	
	/**
	 * �Խ��� ��ȸ
	 */
	@RequestMapping(value="/boardList")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView  mav = new ModelAndView("board");	//board model ����
		try {
			logger.info("AAAAAA");
			Map<String, Object> map =boardService.selectPaging(request);
			mav.setViewName("main/board");					//jsp ���
			mav.addObject("map", map);						//�ѷ��ڵ��
		}
		catch (Exception e) {
			logger.error(e.getMessage());
 			logger.error(e.toString());
		}
 		return mav;
	}
	
	/**
	 * �Խ��� ��
	 * @param board_seq
	 * @return
	 */
	@RequestMapping(value="/boardDetail")
	public ModelAndView boardDetail(HttpServletRequest request) {
		Map<String, Object> map = boardService.boardDetail(request);
		ModelAndView  mav = new ModelAndView("boardDetail");
		mav.setViewName("main/boardDetail");			//JSP ���
		mav.addObject("boardList", map.get("boardList"));//�Խ��� ����Ʈ
		return mav;
	}

	/**
	 * �Խ��� ������Ʈ
	 * @param board_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateBoard")
	public String updateBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="board_title") String board_title
							, @RequestParam(value="board_content") String board_content
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("board_seq : "+board_seq);
		logger.info("board_title : "+board_title);
		logger.info("board_content : "+board_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ������Ʈ map
			map.put("board_seq", board_seq);//�Խñ� ������
			map.put("board_title", board_title.replace("\r\n","<br>"));//�Խñ� ����
			map.put("board_content", board_content.replace("\r\n","<br>"));//�Խñ� ����
			map.put("user_name", user_name);//����������
			 
			resultCode = boardService.updateBoard(map);//�Խñ� ������Ʈ
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	
	/**
	 * �Խ��� ����
	 * @param board_seq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteBoard")
	public String deleteBoard(@RequestParam(value="board_seq") int board_seq
							, @RequestParam(value="user_name") String user_name) {
		
		logger.info("start");
		logger.info("board_seq : "+board_seq);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("board_seq", board_seq);//�Խñ� ������
			map.put("user_name", user_name);//����������
			
			resultCode = boardService.deleteBoard(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
		
		
		logger.info("end");
		
		return resultCode;
	}
	
	/**
	 * �Խñ� �ۼ� ������ �̵�
	 * @return
	 */
	@RequestMapping(value="/writeBoard")
	public ModelAndView boardWrite() {
				
		ModelAndView  mav = new ModelAndView("boardWrite");
		mav.setViewName("main/boardWrite");//jsp ���
		
		return mav;
	}
	
	/**
	 * �Խñ� �ۼ�
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertBoard")
	public String boardInsert(@RequestParam(value="board_title", required =false) String board_title
							  , @RequestParam(value="board_content", required =false) String board_content
							  , @RequestParam(value="user_name", required =false) String user_name) {
		
		logger.info("start");
		logger.info("board_title : "+board_title);
		logger.info("board_content : "+board_content);
		logger.info("user_name : "+user_name);
		
		String resultCode = "0000";//0000:���� / 9000:����
		
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();//�Խñ� ���� map
			map.put("board_title", board_title);//�Խñ� ����
			map.put("board_content", board_content);//�Խñ� ����
			map.put("created_by", user_name);//������
			map.put("last_update_by", user_name);//����������
			
			resultCode = boardService.insertBoard(map);//�Խñ� ����
			
		}catch(Exception e) {

			logger.error(e.toString());
			resultCode = "9000";
			
		}//try
				
		logger.info("end");
		
		return resultCode;
		
	}
	
}
