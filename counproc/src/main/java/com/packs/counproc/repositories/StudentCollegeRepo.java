package com.packs.counproc.repositories;

import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentCollegeRepo extends MongoRepository<StudentCollegeMap, Integer> {
}
