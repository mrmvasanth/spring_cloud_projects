package com.packs.counproc.MongoServer.models.AssessmentModels;

import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssessmentsDetails {

    @Id
    String id;

    int assessId;

    @NotNull @NotBlank
    String assessName;

    @NotNull @NotBlank @Valid
    JSONObject subjectsAndMarks;

    int totalMark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAssessId() {
        return assessId;
    }

    public void setAssessId(int assessId) {
        this.assessId = assessId;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public JSONObject getSubjectsAndMarks() {
        return subjectsAndMarks;
    }

    public void setSubjectsAndMarks(JSONObject subjectsAndMarks) {
        this.subjectsAndMarks = subjectsAndMarks;
    }

    public int getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(int totalMark) {
        this.totalMark = totalMark;
    }
}
