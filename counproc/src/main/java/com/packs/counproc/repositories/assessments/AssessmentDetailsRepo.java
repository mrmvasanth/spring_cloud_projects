package com.packs.counproc.repositories.assessments;

import com.packs.counproc.models.AssessmentModels.AssessmentsDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssessmentDetailsRepo extends MongoRepository<AssessmentsDetails, String> {
}
