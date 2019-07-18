package com.packs.counproc.repositories;

import com.packs.counproc.models.CollegeModels.Colleges;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollegesRepo extends MongoRepository<Colleges, Integer> {
     public Colleges findByCollegeName(String collegeName);

}
