package com.bookstore.controller;

import com.bookstore.entity.Order;
import com.bookstore.entity.User;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        try {
            Order order = orderService.createOrder(user);
            return "redirect:/order/confirmation/" + order.getId();
        } catch (RuntimeException e) {
            return "redirect:/cart?error=empty"; // Handle empty cart
        }
    }

    @GetMapping("/order/confirmation/{id}")
    public String orderConfirmation(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        return "order_confirmation";
    }

    @GetMapping("/orders")
    public String myOrders(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        List<Order> orders = orderService.findOrdersByUser(user);
        model.addAttribute("orders", orders);
        return "my_orders";
    }
}
