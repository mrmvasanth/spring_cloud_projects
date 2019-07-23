package com.packs.counproc.models.AssessmentModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.simple.JSONObject;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssessmentScores {
    @Id
    String id;

    int assessId;

    int studentId;

    @NotNull @NotBlank  @Valid
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
