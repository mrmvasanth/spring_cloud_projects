package com.packs.counproc.MongoServer.repositories.college;

import com.packs.counproc.MongoServer.models.CollegeModels.Classrooms;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassroomRepo extends MongoRepository<Classrooms, String> {

    Classrooms findBySectionId(int sectionId);

    List<Classrooms> findByCollegeId(int collegeId);

}
