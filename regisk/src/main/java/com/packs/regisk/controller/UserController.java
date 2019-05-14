package com.packs.regisk.controller;

import com.packs.regisk.entity.UserDetail;
import com.packs.regisk.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/signup")
    public UserDetail createUser(@Valid @RequestBody UserDetail userDetail) {
        return userRepo.save(userDetail);
    }

    @GetMapping("/getalluser")
    public List<UserDetail> allUser(){
        return userRepo.findAll();
    }

}
