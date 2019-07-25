package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.MongoServer.repositories.assessments.AssessmentDetailsRepo;
import com.packs.counproc.MongoServer.repositories.assessments.AssessmentScoresRepo;
import com.packs.counproc.MongoServer.repositories.college.ClassroomRepo;
import com.packs.counproc.MongoServer.repositories.college.CollegesRepo;
import com.packs.counproc.MongoServer.repositories.college.DepartmentListRepo;
import com.packs.counproc.MongoServer.repositories.college.StudentClassRepo;
import com.packs.counproc.MongoServer.repositories.register.RegisterStudentRepo;
import com.packs.counproc.MongoServer.repositories.register.StudentCollegeRepo;
import com.packs.counproc.models.ApiResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public class CleanService {

    @Autowired
    AssessmentScoresRepo assessmentScoresRepo;
    @Autowired
    AssessmentDetailsRepo assessmentDetailsRepo;
    @Autowired
    ClassroomRepo classroomRepo;
    @Autowired
    CollegesRepo collegesRepo;
    @Autowired
    DepartmentListRepo departmentListRepo;
    @Autowired
    RegisterStudentRepo registerStudentRepo;
    @Autowired
    StudentCollegeRepo studentCollegeRepo;
    @Autowired
    StudentClassRepo studentClassRepo;

    public ApiResponse cleanDatabase() {
        assessmentScoresRepo.deleteAll();
        assessmentDetailsRepo.deleteAll();;
        classroomRepo.deleteAll();
        collegesRepo.deleteAll();;
        departmentListRepo.deleteAll();
        registerStudentRepo.deleteAll();
        studentCollegeRepo.deleteAll();
        studentClassRepo.deleteAll();
        return new ApiResponse(200, HttpStatus.OK, "Cleaned");
    }

    public ResponseEntity<ApiResponseBody> deleteAllCollege(){
        collegesRepo.deleteAll();
        ApiResponseBody apiResponseBody=new ApiResponseBody("All colleges deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> deleteAllDepartments(){
        departmentListRepo.deleteAll();
        ApiResponseBody apiResponseBody=new ApiResponseBody("All departments deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> deleteAllClassrooms(){
        classroomRepo.deleteAll();
        ApiResponseBody apiResponseBody=new ApiResponseBody("All Classrooms deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

}
