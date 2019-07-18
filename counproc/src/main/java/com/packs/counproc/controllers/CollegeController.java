package com.packs.counproc.controllers;

import com.packs.counproc.models.CollegeModels.Colleges;
import com.packs.counproc.models.requests.AddDepartment;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.services.CollegeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CollegeController {

    @Autowired
    CollegeServices collegeServices;

    @PostMapping("/clg/add")
    public ApiResponse addCollege(@RequestBody Colleges college){
        return collegeServices.addCollege(college);
    }

    @GetMapping("/clg/getall")
    public ApiResponse getAllColleges(){
        return collegeServices.getAllColleges();
    }

    @PostMapping("/dept/add")
    public ApiResponse addDepartment(@RequestBody AddDepartment addDepartment){
        return collegeServices.addDepartment(addDepartment);
    }

    @GetMapping("/dept/getall")
    public ApiResponse getAllDepartment(){
        return collegeServices.getAllDepartment();
    }
}
