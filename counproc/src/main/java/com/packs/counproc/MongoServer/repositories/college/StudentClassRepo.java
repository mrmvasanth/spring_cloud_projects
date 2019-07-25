package com.packs.counproc.MongoServer.repositories.college;

import com.packs.counproc.MongoServer.models.CollegeModels.StudentClassMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentClassRepo extends MongoRepository<StudentClassMap, String> {


}
