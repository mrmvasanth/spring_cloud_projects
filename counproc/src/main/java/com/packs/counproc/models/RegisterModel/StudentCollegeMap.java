package com.packs.counproc.models.RegisterModel;

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

    String studentId;

    String collegeId;

    String departmentId;


}
