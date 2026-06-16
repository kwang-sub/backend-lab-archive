package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository repository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(member)
                    .build();

            repository.save(board);
        });
    }

    @Test
    public void testRead1() {
        Optional<Board> result = repository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadWithWriter() {
        Object result= repository.getBoardWithWriter(100L);

        Object[] arr = (Object[]) result;
        System.out.println("==================================");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReadWithReply() {
        Object result = repository.getBoardWithReply(100L);

        Object[] arr = (Object[]) result;
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageble = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = repository.getBoardWithReplyCount(pageble);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {
        Object[] result = (Object[]) repository.getBoardByBno(100L);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testSearch1() {
        repository.search1();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
                .and(Sort.by("title").ascending()));
        Page<Object[]> result = repository.searchPage("t", "5", pageable);
    }

}