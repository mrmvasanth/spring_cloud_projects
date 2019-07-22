package com.packs.counproc.models.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChooseCollege {

    int regId;
    String collegeName;
    String deptName;
    String counsellingCode;
}
