package com.packs.counproc.services;

import com.packs.counproc.models.DatabaseSequence;
import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.DatabaseSequenceRepo;
import com.packs.counproc.repositories.register.StudentCollegeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilsService {

    @Autowired
    DatabaseSequenceRepo databaseSequenceRepo;

    @Autowired
    StudentCollegeRepo studentCollegeRepo;

    public ApiResponse addSequence(DatabaseSequence addSequence){
        databaseSequenceRepo.save(addSequence);
        return new ApiResponse(200, HttpStatus.OK,"Added");
    }

    public ApiResponse getAllSequence(){
       return new ApiResponse(200, HttpStatus.OK,databaseSequenceRepo.findAll(),"All sequence") ;
    }

    public ApiResponse getStudentCollegeMap(){
        List<StudentCollegeMap> studentCollegeMap=studentCollegeRepo.findAll();
        return new ApiResponse(200, HttpStatus.OK,studentCollegeMap,"Student College Map") ;
    }

}
