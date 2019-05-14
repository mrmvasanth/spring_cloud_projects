package com.packs.jwtdemo.dao;

import com.packs.jwtdemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
}