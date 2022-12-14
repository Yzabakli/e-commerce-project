package com.abakli.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Role role;
}