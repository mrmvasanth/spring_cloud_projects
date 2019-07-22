package com.packs.counproc.controllers;

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

    @GetMapping("clg/assignclasses/{id}")
    public ApiResponse assignclasses(@PathVariable("id") int collegeId){
        return collegeServices.assignclasses(collegeId);
    }

    @GetMapping("clg/studentclassmap")
    public ApiResponse getStudentClassMap(){
        return collegeServices.getStudentClassMap();
    }
}

//    Class Allocation:
//        (1)Assigning the student to the class and provide the section to it .
//        (2)3 assessments provide marks
//        (3) Ensure Multiple database configuration for storing class,section and assessment ,mark details.