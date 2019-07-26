package com.packs.counproc.MongoServer.repositories.college;

import com.packs.counproc.MongoServer.models.CollegeModels.ClassStats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClassStatsRepo extends MongoRepository<ClassStats,String> {
            ClassStats findAllBySectionId(int sectionId);
            Optional<ClassStats> findBySectionId(int sectionId);
}
