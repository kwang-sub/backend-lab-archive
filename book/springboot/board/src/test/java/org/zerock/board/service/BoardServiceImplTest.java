package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Member;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    public void testRegister() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            BoardDTO dto = BoardDTO.builder()
                    .title("Test")
                    .content("test")
                    .writerEmail("user"+i+"@aaa.com")
                    .build();
            Long bno = service.register(dto);
            System.out.println(bno);
        });
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = service.get(bno);
        System.out.println(boardDTO);
    }

    @Test
    public void testRemove() {
        Long bno = 200L;
        service.removeWithReplies(bno);
    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(302L)
                .title("한글!")
                .content("modify")
                .build();
        service.modify(boardDTO);
    }
}