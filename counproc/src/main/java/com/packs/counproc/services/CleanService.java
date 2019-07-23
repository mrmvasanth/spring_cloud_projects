package com.packs.counproc.services;

import com.packs.counproc.models.AssessmentModels.AssessmentScores;
import com.packs.counproc.models.AssessmentModels.AssessmentsDetails;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.assessments.AssessmentDetailsRepo;
import com.packs.counproc.repositories.assessments.AssessmentScoresRepo;
import com.packs.counproc.repositories.college.ClassroomRepo;
import com.packs.counproc.repositories.college.CollegesRepo;
import com.packs.counproc.repositories.college.DepartmentListRepo;
import com.packs.counproc.repositories.college.StudentClassRepo;
import com.packs.counproc.repositories.register.RegisterStudentRepo;
import com.packs.counproc.repositories.register.StudentCollegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
