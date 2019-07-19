package com.packs.counproc.controllers;

import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.models.requests.ChooseCollege;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.services.RegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/co")
public class CounsellingController {

    @Autowired
    RegisterServices registerService;

    @GetMapping("/")
    public ApiResponse counproc() {
        return registerService.testCounProcServer();
    }

    @PostMapping("/register")
    public ApiResponse registerStudents(@RequestBody RegisterStudent student) {
        return registerService.resgisterStudent(student);
    }

    @GetMapping("/getall")
    public ApiResponse getAllStudent() {
        return registerService.getAllStudents();
    }

    @GetMapping("/sendcalls")
    public ApiResponse sendEmails() {
        return registerService.sendEmails();
    }

    @PostMapping("/choosecollege")
    public ApiResponse chooseCollege(@RequestBody ChooseCollege chooseCollege){
        return registerService.chooseCollege(chooseCollege);
    }

}
