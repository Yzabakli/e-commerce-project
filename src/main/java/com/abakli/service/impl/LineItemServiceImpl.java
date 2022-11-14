package com.abakli.service.impl;

import com.abakli.dto.LineItemDTO;
import com.abakli.entity.LineItem;
import com.abakli.mapper.LineItemMapper;
import com.abakli.mapper.OrderMapper;
import com.abakli.mapper.StockItemMapper;
import com.abakli.repository.LineItemRepository;
import com.abakli.service.LineItemService;
import com.abakli.service.OrderService;
import com.abakli.service.StockItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LineItemServiceImpl implements LineItemService {

    private final LineItemRepository lineItemRepository;
    private final OrderService orderService;
    private final StockItemService stockItemService;
    private final LineItemMapper lineItemMapper;
    private final OrderMapper orderMapper;
    private final StockItemMapper stockItemMapper;

    public LineItemServiceImpl(LineItemRepository lineItemRepository, OrderService orderService, StockItemService stockItemService, LineItemMapper lineItemMapper, OrderMapper orderMapper, StockItemMapper stockItemMapper) {
        this.lineItemRepository = lineItemRepository;
        this.orderService = orderService;
        this.stockItemService = stockItemService;
        this.lineItemMapper = lineItemMapper;
        this.orderMapper = orderMapper;
        this.stockItemMapper = stockItemMapper;
    }

    @Override
    public void findByOrder_IdAndStockItem_Id(Long orderId, Long itemId) {

        Optional<LineItem> item = lineItemRepository.findByOrder_IdAndStockItem_Id(orderId, itemId);

        if (item.isEmpty()) {

            LineItem lineItem = new LineItem(orderMapper.convert(orderService.findById(orderId)), stockItemMapper.convert(stockItemService.findById(itemId)), 1, 0);

            lineItemRepository.save(lineItem);
            return;
        }

        LineItem lineItem = item.orElseThrow();

        lineItem.setQuantity(lineItem.getQuantity() + 1);

        //        optional discount

        lineItemRepository.save(lineItem);
    }

    @Override
    public List<LineItemDTO> readAllById(Long id) {


        return lineItemRepository.findByOrder_User_Id(id)
                .stream()
                .map(lineItemMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(Long itemId) {

        LineItem item = lineItemRepository.findByIdAndOrder_User_Id(itemId, 1L); // todo: hardcoded

        if (item.getQuantity() == 1) {

            item.setDeleted(true);
        }
        item.setQuantity(item.getQuantity() - 1);

//        optional discount

        lineItemRepository.save(item);
    }
}
