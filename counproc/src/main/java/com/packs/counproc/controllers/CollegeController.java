package com.packs.counproc.controllers;

import com.packs.counproc.MongoServer.models.CollegeModels.ClassStats;
import com.packs.counproc.MongoServer.models.CollegeModels.Classrooms;
import com.packs.counproc.MongoServer.models.CollegeModels.Colleges;
import com.packs.counproc.MongoServer.models.requests.AddDepartment;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.services.CollegeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class CollegeController {

    @Autowired
    CollegeServices collegeServices;

    @PostMapping("/clg/add")
    public ResponseEntity<ApiResponseBody> addCollege(@RequestBody Colleges college) {
        return collegeServices.addCollege(college);
    }

    @GetMapping("/clg/getall")
    public ResponseEntity<ApiResponseBody> getAllColleges() {
        return collegeServices.getAllColleges();
    }

    @PostMapping("/dept/add")
    public ResponseEntity<ApiResponseBody> addDepartment(@RequestBody AddDepartment addDepartment) {
        return collegeServices.addDepartment(addDepartment);
    }

    @GetMapping("/dept/getall")
    public ResponseEntity<ApiResponseBody> getAllDepartment() {
        return collegeServices.getAllDepartment();
    }

    @PostMapping("clg/classroom/{id}")
    public ResponseEntity<ApiResponseBody> addClassroom(@PathVariable("id") int collegeId, @RequestBody Classrooms classroom) {
        return collegeServices.addClassroom(collegeId, classroom);
    }

    @GetMapping("clg/classroom/{id}")
    public ResponseEntity<ApiResponseBody> getAllClassrooms(@PathVariable("id") int collegeId) {
        return collegeServices.getAllClassrooms(collegeId);
    }

    @DeleteMapping("clg/classroom/{id}")
    public ResponseEntity<ApiResponseBody> deleteClassroom(@PathVariable("id") String classroomId) {
        return collegeServices.deleteClassroom(classroomId);
    }

    @PostMapping("clg/classstats")
    public ResponseEntity<ApiResponseBody> addClassStats(@RequestBody ClassStats classStats) {
        return collegeServices.addClassStats(classStats);
    }

    @GetMapping("clg/classStats")
    public ResponseEntity<ApiResponseBody> getClassStats() {
        return collegeServices.getClassStats();
    }

    @DeleteMapping("clg/deletestats")
    public ResponseEntity<ApiResponseBody> deleteStats() {
        return collegeServices.deleteStats();
    }

    @PostMapping("clg/assignclasses/{id}")
    public ResponseEntity<ApiResponseBody> assignclasses(@PathVariable("id") int collegeId) {
//        return collegeServices.assignclasses(collegeId);
        return collegeServices.assignclasses(collegeId);
    }

    @GetMapping("clg/studentclassmap")
    public ResponseEntity<ApiResponseBody> getStudentClassMap() {
        return collegeServices.getStudentClassMap();
    }
}

// 2 database - DONE
// assessments - DONE
// assign classes based on marks - 2 students > 80, 2 > 70- DONE
// corner cases
