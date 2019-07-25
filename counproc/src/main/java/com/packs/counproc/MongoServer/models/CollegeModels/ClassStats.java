package com.packs.counproc.MongoServer.models.CollegeModels;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class ClassStats {

    @Id
    String id;

    int sectionId;

    int groupOneCount;

    int groupTwoCount;

    int groupThreeCount;

}
