package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.DatabaseSequence;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.MongoServer.repositories.DatabaseSequenceRepo;
import com.packs.counproc.MongoServer.repositories.register.StudentCollegeRepo;
import com.packs.counproc.models.ApiResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<ApiResponseBody> getStudentCollegeMap(){
        ApiResponseBody apiResponseBody=new ApiResponseBody(studentCollegeRepo.findAll(),"Student College Map");
        return ResponseEntity.ok(apiResponseBody);
    }

}
