package com.example.easykiosk.repository;

import com.example.easykiosk.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KioskRepository extends JpaRepository<UserDetails, Long>  {
}
