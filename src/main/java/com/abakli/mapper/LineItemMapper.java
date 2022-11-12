package com.abakli.mapper;

import com.abakli.dto.LineItemDTO;
import com.abakli.entity.LineItem;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LineItemMapper {

    private final ModelMapper mapper;

    public LineItemMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LineItemDTO convertToDTO(LineItem lineItem) {

        return mapper.map(lineItem, LineItemDTO.class);
    }

    public LineItem convert(LineItemDTO dto) {

        return mapper.map(dto, LineItem.class);
    }
}
