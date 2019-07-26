package com.packs.counproc.MongoServer.repositories.college;

import com.packs.counproc.MongoServer.models.CollegeModels.Colleges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollegesRepo extends MongoRepository<Colleges, Integer> {
     Colleges findByCollegeId(int collegeId);

     List<Colleges> findByCollegeName(String collegeName);


     @Query(fields="{ 'collegeName' : 1}")
     public Colleges findById(String id);


}
