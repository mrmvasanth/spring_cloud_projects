package com.packs.counproc.MysqlServer.repositories;

import com.packs.counproc.MysqlServer.models.RegisterStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegRepository extends JpaRepository<RegisterStudent,Integer> {

    List<RegisterStudent> findByCalledOrderByMarksPercentageDesc(boolean status);

}
