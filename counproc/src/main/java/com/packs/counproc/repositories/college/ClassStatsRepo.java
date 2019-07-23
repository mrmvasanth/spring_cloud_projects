package com.packs.counproc.repositories.college;

import com.packs.counproc.models.CollegeModels.ClassStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassStatsRepo extends MongoRepository<ClassStats,String> {
            ClassStats findAllBySectionId(int sectionId);
}
