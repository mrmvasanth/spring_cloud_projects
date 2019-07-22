package com.packs.counproc.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@ToString
public class StudentClassMap {

    @Id
    String id;

    int collegId;

    int deptId;

    String deptName;

    int sectionId;

    String sectionName;

    int studentId;
}
