package jpabook.start.jpabook;

import jpabook.start.jpabook.domain.Board;
import jpabook.start.jpabook.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest {

    @Autowired
    private  BoardRepository boardRepository;

    @Test
    void test() {
        Board board = new Board();
        board = boardRepository.save(board);
        Board board1 =  boardRepository.save(new Board());
        System.out.println("시퀀스를 이용한 저장 test : " + board.getId());
        System.out.println(board1.getId());
    }
}
