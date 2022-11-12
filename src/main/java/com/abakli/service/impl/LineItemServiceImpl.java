package com.abakli.service.impl;

import com.abakli.mapper.LineItemMapper;
import com.abakli.repository.LineItemRepository;
import com.abakli.service.LineItemService;
import org.springframework.stereotype.Service;

@Service
public class LineItemServiceImpl implements LineItemService {

    private final LineItemRepository lineItemRepository;
    private final LineItemMapper lineItemMapper;

    public LineItemServiceImpl(LineItemRepository lineItemRepository, LineItemMapper lineItemMapper) {
        this.lineItemRepository = lineItemRepository;
        this.lineItemMapper = lineItemMapper;
    }
}
