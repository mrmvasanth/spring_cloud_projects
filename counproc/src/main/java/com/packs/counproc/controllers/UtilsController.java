package com.packs.counproc.controllers;

import com.packs.counproc.models.DatabaseSequence;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.services.CleanService;
import com.packs.counproc.services.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    UtilsService utilsService;

    @Autowired
    CleanService cleanService;

    @PostMapping("/seq/add")
    public ApiResponse addSequence(@RequestBody DatabaseSequence addSequence){
        return  utilsService.addSequence(addSequence);
    }

    @GetMapping("/seq/getall")
    public ApiResponse getAllSequence(){
        return utilsService.getAllSequence();
    }

    @GetMapping("studentcollege")
    public ApiResponse getStudentCollegeMap(){
        return utilsService.getStudentCollegeMap();
    }

    @GetMapping("clean")
    public ApiResponse cleanDatabase(){
        return cleanService.cleanDatabase();
    }
}
