package com.packs.counproc.controllers;

import com.packs.counproc.models.AssessmentModels.AssessmentScores;
import com.packs.counproc.models.AssessmentModels.AssessmentsDetails;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.services.AssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assess")
public class AssessmentController {

    @Autowired
    AssessmentsService assessmentsService;

    @GetMapping("/all")
    public ApiResponse getAllAssessments(){
        return assessmentsService.getAllAssessments();
    }

    @PostMapping("/add")
    public ApiResponse addAssessment(@RequestBody AssessmentsDetails assessmentsDetails){
        return assessmentsService.addAssessment(assessmentsDetails);
    }

    @PostMapping("/marks")
    public ApiResponse addMarks(@RequestBody AssessmentScores assessmentScores){
        return assessmentsService.addMarks(assessmentScores);
    }

    @GetMapping("/report")
    public ApiResponse getReport(){
        return assessmentsService.getReport();
    }

    @GetMapping("/report/{id}")
    public ApiResponse getReportByStudent(@PathVariable("id") int studentId){
        return assessmentsService.getReportByStudent(studentId);
    }

}
