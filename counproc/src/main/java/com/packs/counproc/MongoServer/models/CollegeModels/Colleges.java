package com.packs.counproc.MongoServer.models.CollegeModels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Getter
@Setter
@ToString
public class Colleges {
    @Id
    String id;

    int collegeId;

    @Column(name ="collegeName" ,unique = true)
    String collegeName;

}
