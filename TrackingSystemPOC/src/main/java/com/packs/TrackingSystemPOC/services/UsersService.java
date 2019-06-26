package com.packs.TrackingSystemPOC.services;

import com.packs.TrackingSystemPOC.entity.TrackerResponseEntity;
import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public TrackerResponseEntity addUser(Users user) {
        usersRepository.save(user);
        TrackerResponseEntity trackerResponseEntity = new TrackerResponseEntity(HttpStatus.OK, 200, user);
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getAllUsers() {
        TrackerResponseEntity trackerResponseEntity = new TrackerResponseEntity(usersRepository.findAll());
        return trackerResponseEntity;
    }

    public TrackerResponseEntity getUser(int userId) {
        TrackerResponseEntity trackerResponseEntity;
        Optional<Users> user=usersRepository.findById(userId);
        if (user.isPresent()){
            trackerResponseEntity=new TrackerResponseEntity(HttpStatus.OK,200,user);
        }else {
            trackerResponseEntity=new TrackerResponseEntity(404,HttpStatus.NOT_FOUND,"No user with given ID found");
        }
        return trackerResponseEntity;
    }

    public TrackerResponseEntity deleteUser(int userId) {
        TrackerResponseEntity trackerResponseEntity;
        Optional<Users> user=usersRepository.findById(userId);
        if (user.isPresent()){
            usersRepository.deleteById(userId);
            return new TrackerResponseEntity(200,HttpStatus.OK, "User Deleted");
        }else{
            return new TrackerResponseEntity(404,HttpStatus.NOT_FOUND, "User with given ID not found");
        }

    }
}
