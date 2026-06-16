package com.example.chap11.domain.item;

import com.example.chap11.domain.Category;
import com.example.chap11.exception.NotEnoughStockException;
import lombok.*;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Getter
@Setter
@ToString
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @Version()
    private Integer version;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();


    public void addCategories(Category category) {
        this.categories.add(category);
        category.getItems().add(this);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int stockQuantity) {
        int restStock = this.stockQuantity - stockQuantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("재고 수량 부족");
        }
        this.stockQuantity = restStock;
    }
}
