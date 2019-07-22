package com.packs.counproc.repositories.assessments;

import com.packs.counproc.models.AssessmentModels.AssessmentScores;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssessmentScoresRepo extends MongoRepository<AssessmentScores,String> {

    List<AssessmentScores> findAllByStudentId(int studentId);
}
