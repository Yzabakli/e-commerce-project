package com.abakli.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "line_items")
@Where(clause = "is_deleted = false")
public class LineItem extends BaseEntity {

    @ManyToOne
    private Order order;

    @ManyToOne
    private StockItem stockItem;

    private Integer quantity;
    private Integer discount;
    private boolean isPayed;
}