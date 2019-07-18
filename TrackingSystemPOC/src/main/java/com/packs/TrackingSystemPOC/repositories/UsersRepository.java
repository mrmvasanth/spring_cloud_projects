package com.packs.TrackingSystemPOC.repositories;

import com.packs.TrackingSystemPOC.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
