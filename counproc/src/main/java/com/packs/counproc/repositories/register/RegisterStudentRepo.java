package com.packs.counproc.repositories.register;

import com.packs.counproc.models.RegisterModel.RegisterStudent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RegisterStudentRepo extends MongoRepository<RegisterStudent,Integer> {
    //db.registerStudent.find({called:false}).limit(2);
    List<RegisterStudent> findByCalled(boolean called, Sort sort);
    List<RegisterStudent> findByRegId(int regId);
}
