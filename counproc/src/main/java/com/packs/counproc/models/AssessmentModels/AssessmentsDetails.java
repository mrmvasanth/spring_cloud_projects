package com.packs.counproc.models.AssessmentModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class AssessmentsDetails {

    @Id
    String id;

    int assessId;

    String assessName;

    int noOfQuestion;

    int totalMark;

}
