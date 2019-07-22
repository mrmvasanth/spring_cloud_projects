package com.packs.counproc.repositories;

import com.packs.counproc.models.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequenceRepo extends MongoRepository<DatabaseSequence, String > {
        DatabaseSequence findByModelName(String modelName);

}
