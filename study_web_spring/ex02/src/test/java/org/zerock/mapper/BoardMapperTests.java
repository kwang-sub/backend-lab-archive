package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	/**
	 * 리스트 가져오기 테스트
	 */
	@Test
	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
	}
	
	/**
	 * 글작성 테스트
	 */
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("작성자");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 key");
		board.setContent("새로 작성하는 내용 key");
		board.setWriter("작성자 key");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
		
	}
	
	@Test
	public void testRead() {
		BoardVO board = mapper.read(2L);
		
		log.info("************read*************" + board);
	}
	
	@Test
	public void testDelete() {
		int delete = mapper.delete(1L);
		log.info("삭제---------------------------------------------" + delete);
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setTitle("수정제목");
		board.setContent("수정 내용");
		board.setWriter("수정자ㅣ");
		board.setBno(3L);
		
		int result = mapper.update(board);
		
		log.info("수정@@@@@@@" +  result);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(1);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		log.info("---------------------------paging");
		list.forEach(b -> log.info(b));
	}
	
	@Test
	public void testSearch() {
		log.info("=======test===================");
		Criteria cri = new Criteria();
		cri.setKeyword("새로");
		cri.setType("TC");
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
}
