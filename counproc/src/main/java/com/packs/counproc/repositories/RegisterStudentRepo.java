package com.packs.counproc.repositories;

import com.packs.counproc.models.RegisterModel.RegisterStudent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RegisterStudentRepo extends MongoRepository<RegisterStudent,Integer> {
    List<RegisterStudent> findByCalled(boolean called, Sort sort);


}
