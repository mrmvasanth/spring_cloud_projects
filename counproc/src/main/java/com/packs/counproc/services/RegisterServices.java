package com.packs.counproc.services;

import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.RegisterStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServices {

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    public ApiResponse testCounProcServer(){
        return new ApiResponse(200,"CounProc Server Alive");
    }

    public ApiResponse resgisterStudent(RegisterStudent student){
        student.setRegisteredOn(new Date());
        student.setCalled(false);
        registerStudentRepo.save(student);
        return new ApiResponse(200, HttpStatus.OK,"Registration Success");
    }

    public ApiResponse getAllStudents(){
        List studentsList=registerStudentRepo.findAll();
        return new ApiResponse(200,HttpStatus.OK,studentsList,"All registered students");
    }

    public ApiResponse sendEmails(){
        // get students with called status=false
        List<RegisterStudent> registerStudents=registerStudentRepo.findByCalled(false);

        // sort desc based on mark percentage


        // get top 3 students mail and send mail

        // change status to true

        return new ApiResponse(200,HttpStatus.OK,"Emails sent");
    }
}
