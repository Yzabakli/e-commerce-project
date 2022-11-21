package com.abakli.controller;

import com.abakli.service.LineItemService;
import com.abakli.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final LineItemService lineItemService;

    public OrderController(OrderService orderService, LineItemService lineItemService) {
        this.orderService = orderService;
        this.lineItemService = lineItemService;
    }

    @GetMapping("/order-detail/{orderId}")
    public String readOrder(@PathVariable("orderId") Long orderId, Model model) {

        model.addAttribute("order", orderService.findById(orderId));

        return "customer/read-order";
    }

    @GetMapping("/pay/{orderId}")
    public String payOrder(@PathVariable("orderId") Long orderId) {

        orderService.payOrder(orderId);

        return "redirect:/item/show";
    }

    @GetMapping("/history")
    public String readOrderHistory(Model model) {

        model.addAttribute("orders", orderService.readAllPayed());

        return "customer/order-history";
    }

    @GetMapping("/history/order-detail/{id}")
    public String readOrderHistoryDetail(@PathVariable Long id, Model model) {

        model.addAttribute("lineItems", lineItemService.findByOrder_Id(id));

        return "customer/item/list-line-items";
    }
}
