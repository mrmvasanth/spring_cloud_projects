package com.packs.TrackingSystemPOC.services;

import com.packs.TrackingSystemPOC.constants.StatusEnum;
import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public void placeOrder(Orders order) {
        StatusEnum status=StatusEnum.valueOf(order.getStatus());
        order.setStatus(status.getStatus());
        ordersRepository.save(order);
    }

    public void deleteOrderById(int orderId) {
        ordersRepository.deleteById(orderId);
    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> getOrderByOrderId(int orderId) {
        return ordersRepository.findById(orderId);
    }

    public List<Orders> getOrdersByUserId(int userId) {
        return ordersRepository.findOrdersByUserId(userId);
    }

    public List<Orders> getOrderByStatus(String statusCode) {
        StatusEnum status=StatusEnum.valueOf(statusCode);
        String stat=status.getStatus();
        return ordersRepository.findOrdersByStatus(stat);
    }

}




