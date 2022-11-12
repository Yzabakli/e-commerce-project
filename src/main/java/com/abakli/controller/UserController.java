package com.abakli.controller;

import com.abakli.dto.UserDTO;
import com.abakli.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/create")
    public String createAdmin(Model model) {

        model.addAttribute("newAdmin", new UserDTO());

        return "admin/user/create";
    }

    @PostMapping("/admin/create")
    public String createAdmin(@ModelAttribute("newAdmin") UserDTO newAdmin) {

        userService.save(newAdmin);

        return "redirect:/user/admin/create";
    }

    @GetMapping("/create")
    public String createCustomer(Model model) {

        model.addAttribute("newCustomer", new UserDTO());
        model.addAttribute("customers", userService.findAllCustomers());

        return "admin/user/create-customer";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("newCustomer") UserDTO newCustomer) {

        userService.save(newCustomer);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{taskId}")
    public String updateCustomer(@PathVariable("taskId") Long id, Model model) {

        model.addAttribute("editCustomer", userService.findById(id));
        model.addAttribute("customers", userService.findAllCustomers());

        return "admin/user/update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@ModelAttribute("editCustomer") UserDTO editCustomer) {

        userService.update(editCustomer);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteCustomer(@PathVariable("taskId") Long id) {

        userService.delete(id);

        return "redirect:/user/create";
    }

}