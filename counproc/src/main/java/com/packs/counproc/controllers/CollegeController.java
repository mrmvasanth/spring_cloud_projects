package com.packs.counproc.controllers;

import com.packs.counproc.models.CollegeModels.ClassStats;
import com.packs.counproc.models.CollegeModels.Classrooms;
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

    @PostMapping("clg/classroom/{id}")
    public ApiResponse addClassroom(@PathVariable("id") int collegeId,@RequestBody Classrooms classroom){
        return collegeServices.addClassroom(collegeId,classroom);
    }

    @GetMapping("clg/classroom/{id}")
    public ApiResponse getAllClassrooms(@PathVariable("id") int collegeId){
        return collegeServices.getAllClassrooms(collegeId);
    }

    @PostMapping("clg/assignclasses/{id}")
    public ApiResponse assignclasses(@PathVariable("id") int collegeId){
//        return collegeServices.assignclasses(collegeId);
        return collegeServices.assignclasses(collegeId);
    }

    @GetMapping("clg/studentclassmap")
    public ApiResponse getStudentClassMap(){
        return collegeServices.getStudentClassMap();
    }


    @PostMapping("clg/classStats")
    public ApiResponse addClassStats(@RequestBody ClassStats classStats){
        return collegeServices.addClassStats(classStats);
    }

    @GetMapping("clg/classStats")
    public ApiResponse getClassStats(){
        return collegeServices.getClassStats();
    }
}

// 2 database
// assessments - DONE
// assign classes based on marks - 2 students > 80, 2 > 70- DONE
// corner cases
