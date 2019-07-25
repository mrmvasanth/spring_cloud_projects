package com.packs.counproc.MongoServer.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class DepartmentList {
    @Id
    String id;

    int deptId;

    int collegeId;

    String deptName;

    String deptDesc;

    int inTakeCount;

}
