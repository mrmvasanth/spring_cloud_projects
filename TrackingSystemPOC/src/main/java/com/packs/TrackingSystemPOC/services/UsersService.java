package com.packs.TrackingSystemPOC.services;

import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users addUser(Users user){
        usersRepository.save(user);
        return user;
    }

    public List<Users> getAllUsers(){
        return (List<Users>) usersRepository.findAll();
    }

    public Optional<Users> getUser(int userId){
        return usersRepository.findById(userId);
    }

    public void deleteUser(int userId){
        usersRepository.deleteById(userId);
    }
}
