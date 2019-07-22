package com.packs.counproc.repositories.college;

import com.packs.counproc.models.CollegeModels.StudentClassMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentClassRepo extends MongoRepository<StudentClassMap, String> {
}
