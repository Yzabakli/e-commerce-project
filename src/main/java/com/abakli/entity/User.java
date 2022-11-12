package com.abakli.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@Where(clause = "is_deleted = false")
public class User extends BaseEntity {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;

    public User(String name) {
        this.name = name;
    }
}