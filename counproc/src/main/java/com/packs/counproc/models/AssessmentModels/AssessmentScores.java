package com.packs.counproc.models.AssessmentModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class AssessmentScores {
    @Id
    String id;

    int assessId;

    int studentId;

    int marksScored;
}
