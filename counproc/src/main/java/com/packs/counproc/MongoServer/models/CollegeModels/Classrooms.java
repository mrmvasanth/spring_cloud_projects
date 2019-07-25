package com.packs.counproc.MongoServer.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Classrooms {

    @Id
    String id;

    int collegeId;

    int deptId;

    String deptName;

    int sectionId;

    String sectionName;

    int totalStudents;
}
