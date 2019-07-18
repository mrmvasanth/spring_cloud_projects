package com.packs.counproc.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Colleges {
    @Id
    String id;

    String collegeName;
}
