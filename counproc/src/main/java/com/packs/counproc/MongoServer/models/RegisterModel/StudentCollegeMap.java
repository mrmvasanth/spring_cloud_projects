package com.packs.counproc.MongoServer.models.RegisterModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class StudentCollegeMap {

    @Id
    String id;

    int studentId;

    int collegeId;

    int departmentId;


}
