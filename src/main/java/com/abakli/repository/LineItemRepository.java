package com.abakli.repository;

import com.abakli.entity.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    Optional<LineItem> findByOrder_IdAndStockItem_Id(Long orderId, Long itemId);

    List<LineItem> findByOrder_User_Id(Long id);

    LineItem findByIdAndOrder_User_Id(Long itemId, Long userId);


}