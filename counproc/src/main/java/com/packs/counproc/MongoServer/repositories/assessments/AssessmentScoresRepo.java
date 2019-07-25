package com.packs.counproc.MongoServer.repositories.assessments;

import com.packs.counproc.MongoServer.models.AssessmentModels.AssessmentScores;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssessmentScoresRepo extends MongoRepository<AssessmentScores,String> {

    List<AssessmentScores> findAllByStudentId(int studentId);
}
