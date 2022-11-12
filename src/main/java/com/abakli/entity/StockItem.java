package com.abakli.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "stock_items")
@Where(clause = "is_deleted = false")
public class StockItem extends BaseEntity {

    private String description;
    private BigDecimal price;
}