package com.abakli.repository;

import com.abakli.entity.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

    boolean existsByOrder_IdAndStockItem_Id(Long orderId, Long itemId);
}