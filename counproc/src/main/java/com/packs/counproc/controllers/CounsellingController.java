package com.packs.counproc.controllers;


import com.packs.counproc.MongoServer.models.requests.ChooseCollege;
import com.packs.counproc.MysqlServer.models.RegisterStudent;

import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.services.CounsellingService;
import com.packs.counproc.services.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/con")
public class CounsellingController {

    @Autowired
    CounsellingService counsellingService;

    @Autowired
    RegisterServices registerService;


    @GetMapping("/get")
    public ResponseEntity<ApiResponseBody> getAllRegistredStudents(){
            return counsellingService.getAllRegistredStudents();
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseBody> registerStudent(@Valid @RequestBody RegisterStudent registerStudent){
        return counsellingService.registerStudent(registerStudent);
    }

    @GetMapping("/sendinvites")
    public ResponseEntity<ApiResponseBody> sendInvites(){
        return counsellingService.sendInvites();
    }

    @PostMapping("/choosecollege")
    public ResponseEntity<ApiResponseBody> chooseCollege(@RequestBody ChooseCollege chooseCollege){
        return registerService.chooseCollege(chooseCollege);
    }


}
