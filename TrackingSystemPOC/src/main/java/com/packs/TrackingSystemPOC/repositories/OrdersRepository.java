package com.packs.TrackingSystemPOC.repositories;

import com.packs.TrackingSystemPOC.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findOrdersByUserId(int userId);

    List<Orders> findOrdersByStatus(String status);

}
