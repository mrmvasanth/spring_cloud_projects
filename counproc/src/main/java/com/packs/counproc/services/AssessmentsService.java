package com.packs.counproc.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.packs.counproc.models.AssessmentModels.AssessmentScores;
import com.packs.counproc.models.AssessmentModels.AssessmentsDetails;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.assessments.AssessmentDetailsRepo;
import com.packs.counproc.repositories.assessments.AssessmentScoresRepo;
import com.packs.counproc.utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Service
public class AssessmentsService {

    @Autowired
    Utils utils;

    @Autowired
    AssessmentDetailsRepo assessmentDetailsRepo;

    @Autowired
    AssessmentScoresRepo assessmentScoresRepo;

    public ApiResponse getAllAssessments(){
        List<AssessmentsDetails> assessmentsDetails=assessmentDetailsRepo.findAll();
        return new ApiResponse(200,HttpStatus.OK,assessmentDetailsRepo.findAll(),"All assessments");
    }

    public ApiResponse addAssessment(AssessmentsDetails assessmentsDetails) throws Exception{
        assessmentsDetails.setAssessId(utils.getIncCount("AssessmentId"));
        assessmentDetailsRepo.save(assessmentsDetails);
        return new ApiResponse(200, HttpStatus.OK,"Assessment added");
    }

    public ApiResponse addMarks(AssessmentScores assessmentScores){
        int totalMarksCount=0;
        Iterator marks=assessmentScores.getSubjectsAndMarks().values().iterator();
        while (marks.hasNext()) {
            totalMarksCount=totalMarksCount+(Integer)marks.next();
        }
        assessmentScores.setTotalMark(totalMarksCount);
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
