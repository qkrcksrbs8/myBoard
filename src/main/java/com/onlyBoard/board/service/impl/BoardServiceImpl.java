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
 * �Խ��� ServiceImpl ����
 *
 */
@Repository
public class BoardServiceImpl implements BoardService {

	Logger logger = Logger.getLogger(this.getClass());//�α�
	
	@Autowired
	private BoardDAO boardDAO;//�Խ��� DAO
	
	/* 
	 * �Խñ� ����Ʈ �� ��ȸ	
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
	 * �Խñ� ����Ʈ
	 */
	public List<BoardVO> selectBoardList(Map<String, Object> map) {	
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();//�Խ���VO List
		
		try {
			
			boardList = boardDAO.selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
			
		}catch(Exception e) {
			
			logger.error("selectBoardList()");
			logger.error(e.toString());
			
		}//try
		
		return boardList;
		
	}
	
	/**
	 * �Խñ� ��
	 */
	public BoardVO selectBoard(int board_seq) {
		
		BoardVO boardVO = new BoardVO();//�Խ���VO
		
		try {
			
			boardVO = boardDAO.selectBoard(board_seq);//�Խ��� ����ȸ
			
		}catch(Exception e) {
			
			logger.error("selectBoard()");
			logger.error(e.toString());
			
		}//try
		
		return boardVO;
	
	}
	
	/**
	 * �Խñ� ������Ʈ
	 */
	public String updateBoard(Map<String, Object> map) {

		String resultCode = "0000";// 0000:���� / 9000:����
		
		try {

			boardDAO.updateBoard(map);//�Խñ� ������Ʈ
			resultCode = "0000";// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
			
		}
		
		return resultCode;
	}
	
	/**
	 * �Խñ� ���� (��뿩�θ� ���� 1 -> 0) 
	 * 1:����� / 0:�̻��  
	 */
	public String deleteBoard(Map<String, Object> map) {
		
		String resultCode = "0000";// 0000:���� / 9000:����
		
		try {

			boardDAO.deleteBoard(map);//�Խñ� ������Ʈ
			resultCode = "0000";// 0000:���� / 9000:����
			
		}catch(Exception e) {
			
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
			
		}//try
		
		return resultCode;
	}
	
	public String insertBoard(Map<String, Object> map) {
		String resultCode = "0000";// 0000:���� / 9000:����
		try {
			boardDAO.insertBoard(map);//�Խñ� ����
			resultCode = "0000";// 0000:���� / 9000:����
		}catch(Exception e) {
			logger.error(e.toString());
			resultCode = "9000";// 0000:���� / 9000:����
		}//try
		return resultCode;
	}
	
	/* 
	 * ����¡ó��
	 */
	public Map<String, Object> selectPaging(HttpServletRequest request) {
		return null;
//		Map<String, Object> map = getDefaultPaingMap(request); 
//		String keyField	= Utility.convertNull((String)map.get("keyField"));
//		String keyWord	= Utility.convertNull((String)map.get("keyWord"));
// 		int currentPage = (Integer) map.get("currentPage");
//		int count = selectBoardCnt(map);	 //�Խ��� ����Ʈ �� ��ȸ
//		PagingUtil page;					//����¡ ó���� ���� ��ü ����
//		if (null == keyWord) {//�˻�� �ִٸ�
//			page = new PagingUtil(currentPage, count, 5,2, "boardList.do");							
//		}
//		else {				  //�˻�� ���ٸ�
//			page = new PagingUtil(keyField, keyWord, currentPage, count, 5,2, "boardList.do",null);	
//		}
//		
//		map.put("start", page.getStartCount());	//���� �Խù���ȣ
//		map.put("end", page.getEndCount());		//�������Խù���ȣ
//
//		//---------------------------
//		//�Խù��� 1�� �̻� �����ϸ� ����Ʈ ��ȸ
//		//---------------------------
//		List<BoardVO> boardList = new ArrayList<BoardVO>();		//�Խ��� ����Ʈ
//		if (0 < count ) {
//			boardList = selectBoardList(map);//�Խ��� ����Ʈ ��ȸ
//		};//if
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("count", count);						//�ѷ��ڵ��
//		resultMap.put("boardList", boardList);				//�Խ��� ����Ʈ
//		resultMap.put("pagingHtml", page.getPagingHtml());	//��ũ���ڿ��� ����
//		resultMap.put("keyWord", keyWord);					//�˻��� ����
//		return resultMap;
	}
	
	/**
	 * �Խ��� �� ��ȸ
	 * @param request 
	 */
	public Map<String, Object> boardDetail(HttpServletRequest request) {
		
		String seqStr = (String)request.getParameter("board_seq");
		if ("".equals(seqStr)) return null;
		int boardSeq = Integer.parseInt(request.getParameter("board_seq"));
		BoardVO boardList = selectBoard(boardSeq);//�Խ��� �� ��ȸ
		boardList.getBoard_seq();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", boardList);
		return map;
	}
	
	/**
	 * �Խ��� ����¡ Map
	 * @param request
	 * @return
	 */
	public Map<String, Object>getDefaultPaingMap(HttpServletRequest request){
		int currentPage = 1;//���� ������
		String pageStr	= Utility.convertNull((String)request.getParameter("pageNum"));	//��Ʈ������ ��ȿ�� üũ
		String keyField	= Utility.convertNull((String)request.getParameter("keyField"));	//�˻�����
		String keyWord	= Utility.convertNull((String)request.getParameter("keyWord"));	//�˻���
		if (!"".equals(pageStr)) currentPage = Integer.parseInt(pageStr);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyField", keyField);		//�˻��о�
		map.put("keyWord", keyWord);		//�˻���
		map.put("currentPage", currentPage);//���� ������
		return map;
	}
	
}
