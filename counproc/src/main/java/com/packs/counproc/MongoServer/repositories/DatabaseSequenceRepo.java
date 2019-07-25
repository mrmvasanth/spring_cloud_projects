package com.packs.counproc.MongoServer.repositories;

import com.packs.counproc.MongoServer.models.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequenceRepo extends MongoRepository<DatabaseSequence, String > {
        DatabaseSequence findByModelName(String modelName);

}
