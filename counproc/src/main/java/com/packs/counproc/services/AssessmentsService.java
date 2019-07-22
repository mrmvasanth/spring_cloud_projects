package com.packs.counproc.services;

import com.packs.counproc.models.AssessmentModels.AssessmentScores;
import com.packs.counproc.models.AssessmentModels.AssessmentsDetails;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.assessments.AssessmentDetailsRepo;
import com.packs.counproc.repositories.assessments.AssessmentScoresRepo;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AssessmentsService {

    @Autowired
    Utils utils;

    @Autowired
    AssessmentDetailsRepo assessmentDetailsRepo;

    @Autowired
    AssessmentScoresRepo assessmentScoresRepo;

    public ApiResponse getAllAssessments(){
        return new ApiResponse(200,HttpStatus.OK,assessmentDetailsRepo.findAll(),"All assessments");
    }

    public ApiResponse addAssessment(AssessmentsDetails assessmentsDetails){
        assessmentsDetails.setAssessId(utils.getIncCount("AssessmentId"));
        assessmentDetailsRepo.save(assessmentsDetails);
        return new ApiResponse(200, HttpStatus.OK,"Assessment added");
    }

    public ApiResponse addMarks(AssessmentScores assessmentScores){
        assessmentScoresRepo.save(assessmentScores);
        return new ApiResponse(200, HttpStatus.OK,"Assessment scores added");
    }

    public ApiResponse getReport(){
        return new ApiResponse(200, HttpStatus.OK,assessmentScoresRepo.findAll(),"Assessment Report");
    }

    public ApiResponse getReportByStudent(int studentId){
        return new ApiResponse(200, HttpStatus.OK,assessmentScoresRepo.findAllByStudentId(studentId),"Assessment Report");
    }

}
