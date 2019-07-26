package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.AssessmentModels.AssessmentScores;
import com.packs.counproc.MongoServer.models.AssessmentModels.AssessmentsDetails;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.MongoServer.repositories.assessments.AssessmentDetailsRepo;
import com.packs.counproc.MongoServer.repositories.assessments.AssessmentScoresRepo;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class AssessmentsService {

    @Autowired
    Utils utils;

    @Autowired
    AssessmentDetailsRepo assessmentDetailsRepo;

    @Autowired
    AssessmentScoresRepo assessmentScoresRepo;

    ApiResponseBody apiResponseBody=null;

    public ResponseEntity<ApiResponseBody> getAllAssessments(){

        List<AssessmentsDetails> assessmentsDetails=assessmentDetailsRepo.findAll();
        apiResponseBody=new ApiResponseBody(assessmentDetailsRepo.findAll(),"All assessments");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> addAssessment(AssessmentsDetails assessmentsDetails) throws Exception{
        assessmentsDetails.setAssessId(utils.getIncCount("AssessmentId"));
        assessmentDetailsRepo.save(assessmentsDetails);
        apiResponseBody=new ApiResponseBody(200, HttpStatus.OK,"Assessment added");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> addMarks(AssessmentScores assessmentScores){
        int totalMarksCount=0;
        Iterator marks=assessmentScores.getSubjectsAndMarks().values().iterator();
        while (marks.hasNext()) {
            totalMarksCount=totalMarksCount+(Integer)marks.next();
        }
        assessmentScores.setTotalMark(totalMarksCount);
        assessmentScoresRepo.save(assessmentScores);
        apiResponseBody =new ApiResponseBody(200, HttpStatus.OK,"Assessment scores added");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> getReport(){
        apiResponseBody=new ApiResponseBody(assessmentScoresRepo.findAll (),"Assessment Report");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> getReportByStudent(int studentId){
        apiResponseBody=new ApiResponseBody(assessmentScoresRepo.findAllByStudentId(studentId),"Assessment Report");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> deleteAllAssessments(){
        assessmentDetailsRepo.deleteAll();
        apiResponseBody=new ApiResponseBody(200, HttpStatus.OK,"All Assessment Deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> deleteAllScores(){
        assessmentScoresRepo.deleteAll();
        apiResponseBody=new ApiResponseBody(200, HttpStatus.OK,"All Scores Deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

}

