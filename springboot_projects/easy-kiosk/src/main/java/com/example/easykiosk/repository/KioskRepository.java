package com.example.easykiosk.repository;

import com.example.easykiosk.model.UserDetails;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface KioskRepository extends JpaRepository<UserDetails, Long>  {

    @Query(value = "SELECT * FROM user_details u WHERE u.username= ?1 ", nativeQuery = true)
    public UserDetails findByUsername(String username);
}
