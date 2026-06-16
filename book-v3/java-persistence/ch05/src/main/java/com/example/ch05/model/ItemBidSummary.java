package com.example.ch05.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Subselect(
        value = "select i.ID as ITEMID, i.NAME as NAME, count(b.ID) as NUMBEROFBIDS " +
                "from CE_ITEM i left outer join CE_BID b on i.ID = b.ITEM_ID " +
                "group by i.ID, i.NAME"
)
@Synchronize({"ITEM", "BID"})
@Getter
public class ItemBidSummary {

    @Id
    private Long itemId;
    private String name;
    private int numberOfBids;
}
