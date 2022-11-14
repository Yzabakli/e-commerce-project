package com.abakli.controller;

import com.abakli.dto.StockItemDTO;
import com.abakli.service.LineItemService;
import com.abakli.service.OrderService;
import com.abakli.service.StockItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final StockItemService stockItemService;
    private final LineItemService lineItemService;
    private final OrderService orderService;

    public ItemController(StockItemService stockItemService, LineItemService lineItemService, OrderService orderService) {
        this.stockItemService = stockItemService;
        this.lineItemService = lineItemService;
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public String createItem(Model model) {

        model.addAttribute("item", new StockItemDTO());
        model.addAttribute("items", stockItemService.readAll());

        return "admin/item/item-create";
    }

    @PostMapping("/create")
    public String createItem(@ModelAttribute("item") StockItemDTO stockItemDTO) {

        stockItemService.save(stockItemDTO);

        return "redirect:/item/create";
    }

    @GetMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId, Model model) {

        model.addAttribute("item", stockItemService.findById(itemId));
        model.addAttribute("items", stockItemService.readAll());

        return "admin/item/item-update";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@ModelAttribute("item") StockItemDTO stockItemDTO) {

        stockItemService.update(stockItemDTO);

        return "redirect:/item/create";
    }

    @GetMapping("/delete/{itemId}")
    public String deleteTask(@PathVariable("itemId") Long itemId) {

        stockItemService.delete(itemId);

        return "redirect:/item/create";
    }

    @GetMapping("/show")
    public String listItems(Model model) {

        model.addAttribute("items", stockItemService.readAll());

        return "customer/item/list-items";
    }

    @GetMapping("/add/{itemId}")
    public String addItem(@PathVariable("itemId") Long itemId) {

        lineItemService.findByOrder_IdAndStockItem_Id(orderService.findByUserId(1L).getId(), itemId); // todo: hardcoded

        return "redirect:/item/show";
    }

    @GetMapping("/order")
    public String findItems(Model model) {

        model.addAttribute("lineItems", lineItemService.readAllById(1L)); // todo: hardcoded
        model.addAttribute("stockService", stockItemService);
        model.addAttribute("orderId", orderService.findByUserId(1L).getId()); // todo: hardcoded

        return "customer/item/list-line-items";
    }

    @GetMapping("/remove/{itemId}")
    public String removeItem(@PathVariable("itemId") Long itemId) {

        lineItemService.removeById(itemId);

        return "redirect:/item/order";
    }
}
