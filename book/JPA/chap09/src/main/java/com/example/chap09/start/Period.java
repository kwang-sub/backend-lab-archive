package com.example.chap09.start;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Embeddable
public class Period {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
