package com.packs.counproc.MongoServer.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddDepartment {
    String collegeName;

    String departmentName;

    int inTakeCount;

    String description;

}