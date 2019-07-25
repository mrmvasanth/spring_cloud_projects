package com.packs.counproc.controllers;
import com.packs.counproc.MongoServer.models.AssessmentModels.*;

import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.services.AssessmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assess")
public class AssessmentController {

    @Autowired
    AssessmentsService assessmentsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponseBody> getAllAssessments(){
        return assessmentsService.getAllAssessments();
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseBody> addAssessment(@RequestBody AssessmentsDetails
                                                 assessmentsDetails)throws Exception{
        return assessmentsService.addAssessment(assessmentsDetails);
    }

    @PostMapping("/marks")
    public ResponseEntity<ApiResponseBody> addMarks(@RequestBody AssessmentScores assessmentScores){
        return assessmentsService.addMarks(assessmentScores);
    }

    @GetMapping("/report")
    public ResponseEntity<ApiResponseBody> getReport(){
        return assessmentsService.getReport();
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<ApiResponseBody> getReportByStudent(@PathVariable("id") int studentId){
        return assessmentsService.getReportByStudent(studentId);
    }

}
