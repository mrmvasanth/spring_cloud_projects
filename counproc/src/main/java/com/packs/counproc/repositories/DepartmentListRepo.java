package com.packs.counproc.repositories;

import com.packs.counproc.models.CollegeModels.DepartmentList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DepartmentListRepo extends MongoRepository<DepartmentList, Integer> {
    List<DepartmentList> findByCollegeId(String collegeId);
    DepartmentList findById(String id);
}
