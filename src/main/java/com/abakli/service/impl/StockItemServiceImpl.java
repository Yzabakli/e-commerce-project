package com.abakli.service.impl;

import com.abakli.dto.StockItemDTO;
import com.abakli.entity.StockItem;
import com.abakli.mapper.StockItemMapper;
import com.abakli.repository.StockItemRepository;
import com.abakli.service.StockItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockItemServiceImpl implements StockItemService {

    private final StockItemRepository stockItemRepository;
    private final StockItemMapper mapper;

    public StockItemServiceImpl(StockItemRepository stockItemRepository, StockItemMapper mapper) {
        this.stockItemRepository = stockItemRepository;
        this.mapper = mapper;
    }

    @Override
    public List<StockItemDTO> readAll() {

        return stockItemRepository.findAll().stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(StockItemDTO dto) {

        stockItemRepository.save(mapper.convert(dto));
    }

    @Override
    public StockItemDTO findById(Long itemId) {
        return mapper.convertToDTO(stockItemRepository.findById(itemId).orElseThrow());
    }

    @Override
    public void update(StockItemDTO dto) {

        Optional<StockItem> stockItem = stockItemRepository.findById(dto.getId());
        StockItem convertedStockItem  = mapper.convert(dto);

        if(stockItem.isPresent()){
            stockItemRepository.save(convertedStockItem);
        }
    }

    @Override
    public void delete(Long itemId) {

        StockItem item = stockItemRepository.findById(itemId).orElseThrow();
        item.setDeleted(true);
        stockItemRepository.save(item);
    }
}
