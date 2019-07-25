package com.packs.counproc.MongoServer.repositories.assessments;

import com.packs.counproc.MongoServer.models.AssessmentModels.AssessmentsDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssessmentDetailsRepo extends MongoRepository<AssessmentsDetails, String> {
}
