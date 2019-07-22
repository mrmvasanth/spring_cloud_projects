package com.packs.counproc.repositories.register;

import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentCollegeRepo extends MongoRepository<StudentCollegeMap, String> {

    List<StudentCollegeMap> findByCollegeId(int collegeId);
}
