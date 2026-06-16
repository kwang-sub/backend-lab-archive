package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		
		log.info("*******************" + service);
		assertNotNull(service);
		
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("제목");
		board.setContent("내용");
		board.setWriter("작성자");
		
		service.register(board);
		log.info("작성된 게시글 번호 : " + board.getBno());
	}
	@Test
	public void testGetList() {
		
		Criteria cri = new Criteria();
		List<BoardVO> list = service.getList(cri);
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testDelete() {
		
		log.info("********삭제 여부 :  " + service.remove(10L));
		
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(12L);
		board.setTitle("수정제목");
		board.setContent("수정내용");
		log.info("수정여부 :  " + service.modify(board));
	}
}
