package com.packs.regisk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.packs.regisk.entity.UserDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetail,Long> {

}
