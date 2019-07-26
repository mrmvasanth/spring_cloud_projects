package com.packs.counproc.MongoServer.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Classrooms {

    @Id
    String id;

    int collegeId;

    int deptId;

    @NotNull(message = "Department name cannot be null")
    @NotBlank(message = "Department name cannot be blank")
    @NotEmpty(message = "Department name cannot be empty")
    String deptName;

    int sectionId;

    String sectionName;

    int totalStudents;
}
