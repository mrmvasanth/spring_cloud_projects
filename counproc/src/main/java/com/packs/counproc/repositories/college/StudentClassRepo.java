package com.packs.counproc.repositories.college;

import com.packs.counproc.models.CollegeModels.StudentClassMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentClassRepo extends MongoRepository<StudentClassMap, String> {


}
