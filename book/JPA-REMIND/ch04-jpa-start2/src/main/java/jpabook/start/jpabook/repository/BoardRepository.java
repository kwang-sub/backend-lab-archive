package jpabook.start.jpabook.repository;

import jpabook.start.jpabook.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
