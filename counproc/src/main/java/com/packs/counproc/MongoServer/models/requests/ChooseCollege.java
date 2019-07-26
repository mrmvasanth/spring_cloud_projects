package com.packs.counproc.MongoServer.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class ChooseCollege {
    int regId;
    @Min(value = 4,message = "College name must be greater than 4")
    @Max(value = 150,message = "College name must be greater than 150")
    String collegeName;

    @Min(value = 0,message = "Department name must be greater than 0")
    @Max(value = 50,message = "Department name must be greater than 50")
    String deptName;

    @Size(min = 6,max = 6,message = "Counselling code must be six digit long")
    String counsellingCode;
}
