package com.packs.counproc.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AvailableColleges {
    String collegeName;
    String departmentName;
    int availableSeats;
}
