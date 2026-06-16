package com.example.chap07.start.distinction.one;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Board {
    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;
    @OneToOne(mappedBy = "board")
    private BoardDetail boardDetail;
}
