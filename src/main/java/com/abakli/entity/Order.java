package com.abakli.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
@Where(clause = "is_deleted = false")
public class Order extends BaseEntity {

    @ManyToOne
    private User user;
    private LocalDate orderDate;
    private LocalDate shipDate;
    private boolean isPayed;
}