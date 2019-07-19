package com.packs.counproc.repositories;

import com.packs.counproc.models.CollegeModels.Colleges;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CollegesRepo extends MongoRepository<Colleges, Integer> {
     public List<Colleges> findByCollegeName(String collegeName);

     @Query(fields="{ 'collegeName' : 1}")
     public Colleges findById(String id);


}
