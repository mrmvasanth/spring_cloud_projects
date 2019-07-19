package com.packs.counproc.services;

import com.packs.counproc.models.CollegeModels.Colleges;
import com.packs.counproc.models.CollegeModels.DepartmentList;
import com.packs.counproc.models.requests.AddDepartment;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.CollegesRepo;
import com.packs.counproc.repositories.DepartmentListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServices {

    @Autowired
    CollegesRepo collegesRepo;

    @Autowired
    DepartmentListRepo departmentListRepo;

    public ApiResponse getAllColleges(){
        return new ApiResponse(200, HttpStatus.OK, collegesRepo.findAll(), "All Colleges");
    }

    public ApiResponse addCollege(Colleges college) {
        Colleges collegeObj = collegesRepo.save(college);
        return new ApiResponse(200, HttpStatus.OK, collegeObj.getId(), "College Added");
    }

    public ApiResponse getAllDepartment(){
        return new ApiResponse(200,HttpStatus.OK,departmentListRepo.findAll(),"All Departments");
    }

    public ApiResponse addDepartment(AddDepartment addDepartment) {
        List<Colleges> colleges = collegesRepo.findByCollegeName(addDepartment.getCollegeName());
        if (!colleges.isEmpty()) {
            DepartmentList department = new DepartmentList();
            department.setCollegeId(colleges.get(0).getId());
            department.setDeptName(addDepartment.getDepartmentName());
            department.setDeptDesc(addDepartment.getDescription());
            department.setInTakeCount(addDepartment.getInTakeCount());
            departmentListRepo.save(department);
            return new ApiResponse(200, HttpStatus.OK, "Department Added");
        }else{
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid college");
        }

    }

}
