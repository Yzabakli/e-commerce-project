package com.abakli.controller;

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

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
}
