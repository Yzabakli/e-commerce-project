package com.abakli.mapper;

import com.abakli.dto.StockItemDTO;
import com.abakli.entity.StockItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StockItemMapper {

    private final ModelMapper mapper;

    public StockItemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StockItemDTO convertToDTO(StockItem item) {

        return mapper.map(item, StockItemDTO.class);
    }

    public StockItem convert(StockItemDTO dto) {

        return mapper.map(dto, StockItem.class);
    }
}
