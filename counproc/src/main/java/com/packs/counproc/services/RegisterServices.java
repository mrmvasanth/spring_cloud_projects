package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.CollegeModels.Colleges;
import com.packs.counproc.MongoServer.models.CollegeModels.DepartmentList;
import com.packs.counproc.MongoServer.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.MongoServer.models.requests.ChooseCollege;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.MongoServer.models.responses.AvailableColleges;
import com.packs.counproc.MongoServer.repositories.college.CollegesRepo;
import com.packs.counproc.MongoServer.repositories.college.DepartmentListRepo;
import com.packs.counproc.MongoServer.repositories.register.RegisterStudentRepo;
import com.packs.counproc.MongoServer.repositories.register.StudentCollegeRepo;
import com.packs.counproc.MysqlServer.models.RegisterStudent;
import com.packs.counproc.MysqlServer.repositories.StudentRegRepository;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterServices {

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    @Autowired
    StudentRegRepository studentRegRepository;

    @Autowired
    CollegesRepo collegesRepo;

    @Autowired
    DepartmentListRepo departmentListRepo;

    @Autowired
    StudentCollegeRepo studentCollegeRepo;

    @Autowired
    MailServices mailServices;

    @Autowired
    Utils utils;


    public ApiResponse testCounProcServer() {
        return new ApiResponse(200, "CounProc Server Alive");
    }

//    public ApiResponse resgisterStudent(RegisterStudent student) {
//        student.setRegisteredOn(new Date());
//        student.setCalled(false);
//        student.setRegId(utils.getIncCount("RegisterId"));
//        registerStudentRepo.save(student);
//        String response = "Your registration ID is: " + student.getRegId();
//        return new ApiResponse(200, HttpStatus.OK, response, "Registration Success");
//    }

//    public ApiResponse getAllStudents() {
//        List studentsList = registerStudentRepo.findAll();
//        return new ApiResponse(200, HttpStatus.OK, studentsList, "All registered students");
//    }

    public ResponseEntity<ApiResponseBody> chooseCollege(ChooseCollege chooseCollege) {
        if (utils.validateStudent(chooseCollege)) {
            return checkAndEnroll(chooseCollege);
        } else {
            ApiResponseBody apiResponseBody = new ApiResponseBody(400, HttpStatus.BAD_REQUEST, "Invalid Reg Id/counsellingCode or already processed");
            return ResponseEntity.ok(apiResponseBody);
        }
    }

    ResponseEntity<ApiResponseBody> checkAndEnroll(ChooseCollege chooseCollege) {
        ApiResponseBody apiResponseBody = null;
        List<AvailableColleges> availableCollegesList = new ArrayList<>();
        ApiResponse apiResponse = new ApiResponse();
        List<Colleges> colleges = collegesRepo.findByCollegeName(chooseCollege.getCollegeName());
        if (!colleges.isEmpty()) {
            List<DepartmentList> departmentList = departmentListRepo.findByCollegeId(colleges.get(0).getCollegeId());
            for (DepartmentList departments : departmentList) {
                if (departments.getInTakeCount() > 0 && departments.getDeptName().equals(chooseCollege.getDeptName())) {
                    // enroll the student to the college and dept
                    return  enrollStudent(chooseCollege, departments);
                } else {
                    if (departments.getInTakeCount() > 0) {
                        Colleges collegeObj = collegesRepo.findByCollegeId(departments.getCollegeId());

                        AvailableColleges college = new AvailableColleges();

                        college.setAvailableSeats(departments.getInTakeCount());
                        college.setDepartmentName(departments.getDeptName());
                        college.setCollegeName(collegeObj.getCollegeName());

                        availableCollegesList.add(college);
                    }
                    // get list of available departments
                    apiResponseBody = new ApiResponseBody(availableCollegesList, "Requested department not available, select from following ");
                }
            }
            return ResponseEntity.ok(apiResponseBody);
        } else {
            apiResponseBody = new ApiResponseBody(400, HttpStatus.BAD_REQUEST, "Invalid College");
            return ResponseEntity.ok(apiResponseBody);
        }
    }

    ResponseEntity<ApiResponseBody> enrollStudent(ChooseCollege chooseCollege, DepartmentList department) {
        ApiResponseBody apiResponseBody = null;
        try {
//            List<RegisterStudent> student = registerStudentRepo.findByRegId(chooseCollege.getRegId());
            Optional<RegisterStudent> student = studentRegRepository.findById(chooseCollege.getRegId());

            // update department table
            department.setInTakeCount(department.getInTakeCount() - 1);
            departmentListRepo.save(department);
            // add to studentCollegeMap table
            StudentCollegeMap studentCollegeMap = new StudentCollegeMap();
            studentCollegeMap.setCollegeId(department.getCollegeId());
            studentCollegeMap.setDepartmentId(department.getDeptId());
            studentCollegeMap.setStudentId(student.get().getId());
            studentCollegeRepo.save(studentCollegeMap);
            student.get().setCollegeAssigned(true);
            studentRegRepository.save(student.get());
            apiResponseBody = new ApiResponseBody(200, HttpStatus.OK, "Student enrolled Successfully");
            return ResponseEntity.ok(apiResponseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }

    }

}
