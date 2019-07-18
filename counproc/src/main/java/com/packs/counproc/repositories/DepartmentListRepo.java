package com.packs.counproc.repositories;

import com.packs.counproc.models.CollegeModels.DepartmentList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentListRepo extends MongoRepository<DepartmentList, Integer> {
}
