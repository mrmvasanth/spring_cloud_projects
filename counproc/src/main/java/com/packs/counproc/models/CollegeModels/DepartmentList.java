package com.packs.counproc.models.CollegeModels;

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

    String collegeId;

    String deptName;

    String deptDesc;

    int inTakeCount;

}
