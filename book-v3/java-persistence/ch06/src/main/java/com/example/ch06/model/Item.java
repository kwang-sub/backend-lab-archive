package com.example.ch06.model;

import com.example.ch06.converter.MonetaryAmountConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME")
    private String name;

    @NotNull
    @Basic(fetch = FetchType.LAZY) // Defaults to EAGER
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING) // Defaults to ORDINAL
    @Builder.Default
    private AuctionType auctionType = AuctionType.HIGHEST_BID;

//    @Formula(
//            "CONCAT(SUBSTR(DESCRIPTION, 1, 12), '...')"
//    )
//    private String shortDescription;
//
//    @Formula(
//            "(SELECT AVG(B.AMOUNT) FROM BID B WHERE B.ITEM_ID = ID)"
//    )
//    private BigDecimal averageBidAmount;

    @Column(name = "IMPERIALWEIGHT")
    @ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462",
            write = "? * 2.20462"
    )
    private double metricWeight;

    @CreationTimestamp
    private LocalDate createdOn;

    @UpdateTimestamp
    private LocalDateTime lastModified;

    @Column(insertable = false)
    @ColumnDefault("1.00")
    @Generated(
            org.hibernate.annotations.GenerationTime.INSERT
    )
    private BigDecimal initialPrice;

    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "PRICE", length = 63)
    private MonetaryAmount buyNowPrice;

    public void setName(String name) {
        this.name = !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
    }
}
