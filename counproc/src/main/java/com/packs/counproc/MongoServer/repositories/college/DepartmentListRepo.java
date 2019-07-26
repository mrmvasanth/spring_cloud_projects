package com.packs.counproc.MongoServer.repositories.college;

import com.packs.counproc.MongoServer.models.CollegeModels.Colleges;
import com.packs.counproc.MongoServer.models.CollegeModels.DepartmentList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DepartmentListRepo extends MongoRepository<DepartmentList, Integer> {
    List<DepartmentList> findByCollegeId(int collegeId);
    List<DepartmentList> findByDeptName(String deptName);
    boolean existsByCollegeIdAndDeptName(int CollegeId, String deptName);

}
