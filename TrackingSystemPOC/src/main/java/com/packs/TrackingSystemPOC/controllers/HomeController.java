package com.packs.TrackingSystemPOC.controllers;

import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.services.OrdersService;
import com.packs.TrackingSystemPOC.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    UsersService usersService;

    @Autowired
    OrdersService ordersService;

    @PostMapping("/adduser")
    private Users addUsers(@RequestBody Users user) {
        usersService.addUser(user);
        return user;
    }

    @GetMapping("/getallusers")
    private List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/getuser/{userid}")
    private Optional<Users> getuser(@PathVariable("userid") int userId) {
        return usersService.getUser(userId);
    }

    @DeleteMapping("/deleteuser/{userid}")
    public void deleteUser(@PathVariable("userid") int userId) {
        usersService.deleteUser(userId);
    }

    @PostMapping("/placeorder")
    private void placeorder(@RequestBody Orders order) {
        ordersService.placeOrder(order);
    }

    @DeleteMapping("/deleteorder/{orderid}")
    public void deleteOrderById(@PathVariable("orderid") int orderId) {
        ordersService.deleteOrderById(orderId);
    }

    @GetMapping("/getallorders")
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/getorder/{orderid}")
    public Optional<Orders> getOrderByOrderId(@PathVariable("orderid") int orderId) {
        return ordersService.getOrderByOrderId(orderId);
    }

    @GetMapping("/getorderbyuserid/{userid}")
    public List<Orders> getOrdersByUserId(@PathVariable("userid") int userId) {
        return ordersService.getOrdersByUserId(userId);
    }

    @GetMapping("/getorderbystatus/{status}")
    public List<Orders> getOrdersByStatus(@PathVariable("status") String status) {
        return ordersService.getOrderByStatus(status);
    }

}
