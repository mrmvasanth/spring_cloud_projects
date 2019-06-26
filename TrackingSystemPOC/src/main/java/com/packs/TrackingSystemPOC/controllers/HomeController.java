package com.packs.TrackingSystemPOC.controllers;

import com.packs.TrackingSystemPOC.constants.StatusEnum;
import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.entity.TrackerResponseEntity;
import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.services.CommonServices;
import com.packs.TrackingSystemPOC.services.OrdersService;
import com.packs.TrackingSystemPOC.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    UsersService usersService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    CommonServices commonServices;

    TrackerResponseEntity trackerResponseEntity;

    @PostMapping("/adduser")
    private ResponseEntity addUsers(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.addUser(user));
    }

    @GetMapping("/getallusers")
    private ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(usersService.getAllUsers());
    }

    @GetMapping("/getuser/{userid}")
    private ResponseEntity getuser(@PathVariable("userid") int userId){
        return ResponseEntity.ok().body(usersService.getUser(userId));
    }

    @GetMapping("/getuserinfo/{userid}")
    private ResponseEntity getUserInfo(@PathVariable("userid") int userId){
        return ResponseEntity.ok().body(commonServices.getUserInfo(userId));
    }

    @DeleteMapping("/deleteuser/{userid}")
    public ResponseEntity deleteUser(@PathVariable("userid") int userId ) {
        return ResponseEntity.ok().body(usersService.deleteUser(userId));
    }

    @PostMapping("/placeorder")
    private ResponseEntity placeorder(@RequestBody Orders order) {
        return ResponseEntity.ok().body(ordersService.placeOrder(order));
    }

    @DeleteMapping("/deleteorder/{orderid}")
    public ResponseEntity deleteOrderById(@PathVariable("orderid") int orderId) {
        return ResponseEntity.ok().body(ordersService.deleteOrderById(orderId));
    }

    @GetMapping("/getallorders")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok().body(ordersService.getAllOrders());
    }

    @GetMapping("/getorder/{orderid}")
    public ResponseEntity getOrderByOrderId(@PathVariable("orderid") int orderId) {
        return ResponseEntity.ok().body(commonServices.getOrderByOrderId(orderId));
    }

    @GetMapping("/getorderbyuserid/{userid}")
    public ResponseEntity getOrdersByUserId(@PathVariable("userid") int userId) {
        return ResponseEntity.ok().body(commonServices.getOrdersByUserId(userId));
    }

    @GetMapping("/getorderbystatus/{status}")
    public ResponseEntity getOrdersByStatus(@PathVariable("status")StatusEnum statusEnum) {
        return ResponseEntity.ok().body(commonServices.getOrderByStatus(statusEnum));
    }
}
