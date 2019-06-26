package com.packs.TrackingSystemPOC.services;

import com.packs.TrackingSystemPOC.constants.StatusEnum;
import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.entity.TrackerResponseEntity;
import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.repositories.OrdersRepository;
import com.packs.TrackingSystemPOC.repositories.UsersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommonServices {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UsersService usersService;

    List<Object> responseData;

    public TrackerResponseEntity getUserInfo(int userId){
        List<Object> res = null;
        res.add(usersRepository.findById(userId));
        res.add(ordersRepository.findOrdersByUserId(userId));
        TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(res);
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getOrderByOrderId(int orderId) {
        Optional<Orders> orders=ordersRepository.findById(orderId);
        TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(orders);
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getOrdersByUserId(int userId) {
            List<Orders> orders=ordersRepository.findOrdersByUserId(userId);
            TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(orders);
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getOrderByStatus(StatusEnum statusEnum) {
        List<Orders> orders=ordersRepository.findOrdersByStatus(statusEnum);
        TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(orders);
        return trackerResponseEntity;
    }
}
