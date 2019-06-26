package com.packs.TrackingSystemPOC.services;

import com.packs.TrackingSystemPOC.entity.Orders;
import com.packs.TrackingSystemPOC.entity.TrackerResponseEntity;
import com.packs.TrackingSystemPOC.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    List<Object> responseData;

    public TrackerResponseEntity placeOrder(Orders order) {
        TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(ordersRepository.save(order));
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getAllOrders() {
        TrackerResponseEntity trackerResponseEntity=new TrackerResponseEntity(ordersRepository.findAll());
        return trackerResponseEntity;
    }

    public TrackerResponseEntity deleteOrderById(int orderId) {
        ordersRepository.deleteById(orderId);
        TrackerResponseEntity trackerResponseEntity= new TrackerResponseEntity(orderId);
        return trackerResponseEntity;
    }



}




