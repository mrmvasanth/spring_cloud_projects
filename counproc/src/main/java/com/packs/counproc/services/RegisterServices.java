package com.packs.counproc.services;

import com.packs.counproc.models.CollegeModels.Colleges;
import com.packs.counproc.models.CollegeModels.DepartmentList;
import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.models.requests.ChooseCollege;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.models.responses.AvailableColleges;
import com.packs.counproc.repositories.CollegesRepo;
import com.packs.counproc.repositories.DepartmentListRepo;
import com.packs.counproc.repositories.RegisterStudentRepo;
import com.packs.counproc.repositories.StudentCollegeRepo;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServices {

    @Autowired
    RegisterStudentRepo registerStudentRepo;

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

    public ApiResponse resgisterStudent(RegisterStudent student) {
        student.setRegisteredOn(new Date());
        student.setCalled(false);
        student.setRegId(utils.getRandomCode("RID"));
        registerStudentRepo.save(student);
        String response = "Your registration ID is: " + student.getRegId();
        return new ApiResponse(200, HttpStatus.OK, response, "Registration Success");
    }

    public ApiResponse getAllStudents() {
        List studentsList = registerStudentRepo.findAll();
        return new ApiResponse(200, HttpStatus.OK, studentsList, "All registered students");
    }

    public ApiResponse sendEmails() {
        boolean flag = false;
        // define sort properties to sord by mark percentage
        Sort sort = new Sort(Sort.Direction.DESC, "marksPercentage");

        // get students with called status=false
        List<RegisterStudent> registerStudents = registerStudentRepo.findByCalled(false, sort);

        if (registerStudents.size() > 0)
            flag = mailServices.sendEmail(registerStudents);
        else
            flag = true;

        if (flag)
            return new ApiResponse(200, HttpStatus.OK, registerStudents, "Emails sent");
        else
            return new ApiResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, "Emails Not sent");
    }

    public ApiResponse chooseCollege(ChooseCollege chooseCollege) {
        if (utils.validateStudent(chooseCollege)) {
            return checkAndEnroll(chooseCollege);
        } else
            return new ApiResponse(400, HttpStatus.BAD_REQUEST, "Invalid Reg Id/counsellingCode or already processed");
    }

    ApiResponse checkAndEnroll(ChooseCollege chooseCollege) {
        List<AvailableColleges> availableCollegesList = new ArrayList<>();
        ApiResponse apiResponse = new ApiResponse();
        boolean enrolledStatus = false;
        List<Colleges> colleges = collegesRepo.findByCollegeName(chooseCollege.getCollegeName());
        if (!colleges.isEmpty()) {
            List<DepartmentList> departmentList = departmentListRepo.findByCollegeId(colleges.get(0).getId());
            for (DepartmentList departments : departmentList) {
                if (departments.getInTakeCount() > 0 && departments.getDeptName().equals(chooseCollege.getDeptName())) {
                    // enroll the student to the college and dept
                    apiResponse = enrollStudent(chooseCollege, departments);
                    break;
                } else {
                    if (departments.getInTakeCount() > 0){
                        Colleges collegeObj=collegesRepo.findById(departments.getCollegeId());
                        AvailableColleges college=new AvailableColleges();
                        college.setAvailableSeats(departments.getInTakeCount());
                        college.setDepartmentName(departments.getDeptName());
                        college.setCollegeName(collegeObj.getCollegeName());
                        availableCollegesList.add(college);
                    }
                    // get list of available departments
                    apiResponse = new ApiResponse(204, HttpStatus.OK, availableCollegesList, "Requested department not available, select from following ");
                }
            }
            return apiResponse;
        } else {
            return new ApiResponse(400, HttpStatus.BAD_REQUEST, "Invalid Reg Id");
        }
    }

    ApiResponse enrollStudent(ChooseCollege chooseCollege, DepartmentList department) {
        try {
            List<RegisterStudent> student = registerStudentRepo.findByRegId(chooseCollege.getRegId());
            // update department table
            department.setInTakeCount(department.getInTakeCount() - 1);
            departmentListRepo.save(department);
            // add to studentCollegeMap table
            StudentCollegeMap studentCollegeMap = new StudentCollegeMap();
            studentCollegeMap.setCollegeId(department.getCollegeId());
            studentCollegeMap.setDepartmentId(department.getId());
            studentCollegeMap.setStudentId(student.get(0).getId());
            studentCollegeRepo.save(studentCollegeMap);
            student.get(0).setAssigned(true);
            registerStudentRepo.save(student.get(0));
            return new ApiResponse(200, "Student enrolled Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }

    }

}
