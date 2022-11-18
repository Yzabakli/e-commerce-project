package com.abakli.repository;

import com.abakli.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_IdAndIsPayedTrue(Long id);

    Optional<Order> findByUser_IdAndIsPayedFalse(Long id);

    boolean existsByUser_Id(Long id);

    Optional<Order> findByUser_Id(Long id);
}