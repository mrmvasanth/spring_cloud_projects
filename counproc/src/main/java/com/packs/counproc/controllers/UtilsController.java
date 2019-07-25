package com.packs.counproc.controllers;

import com.packs.counproc.MongoServer.models.DatabaseSequence;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.services.CleanService;
import com.packs.counproc.services.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    UtilsService utilsService;

    @Autowired
    CleanService cleanService;

    @PostMapping("/seq/add")
    public ApiResponse addSequence(@RequestBody DatabaseSequence addSequence) {
        return utilsService.addSequence(addSequence);
    }

    @GetMapping("/seq/getall")
    public ApiResponse getAllSequence() {
        return utilsService.getAllSequence();
    }

    @GetMapping("studentcollege")
    public ResponseEntity<ApiResponseBody> getStudentCollegeMap() {
        return utilsService.getStudentCollegeMap();
    }

    @GetMapping("clean")
    public ApiResponse cleanDatabase() {
        return cleanService.cleanDatabase();
    }

    @DeleteMapping("deletecolleges")
    public ResponseEntity<ApiResponseBody> deleteAllColleges() {
        return cleanService.deleteAllCollege();
    }

    @DeleteMapping("deletedepartments")
    public ResponseEntity<ApiResponseBody> deleteAllDepartments() {
        return cleanService.deleteAllDepartments();
    }

    @DeleteMapping("deleteclassrooms")
    public ResponseEntity<ApiResponseBody> deleteAllClassrooms() {
        return cleanService.deleteAllClassrooms();
    }
}
