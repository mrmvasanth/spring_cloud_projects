package com.packs.counproc.repositories.college;

import com.packs.counproc.models.CollegeModels.Classrooms;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassroomRepo extends MongoRepository<Classrooms, String> {

    Classrooms findBySectionId(int sectionId);

    List<Classrooms> findByCollegeId(int collegeId);

    List<Classrooms> findByCollegeIdAndDeptId(int collegeId,int deptId);
}
