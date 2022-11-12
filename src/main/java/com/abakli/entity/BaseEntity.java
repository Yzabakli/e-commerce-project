package com.abakli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime insertDateTime;

    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    private boolean isDeleted;

    @PrePersist
    private void onInsert() {

        this.insertDateTime = LocalDateTime.now();
        this.updateDateTime = LocalDateTime.now();
        this.isDeleted = false;
    }

    @PreUpdate
    private void onUpdate() {

        this.updateDateTime = LocalDateTime.now();
    }
}