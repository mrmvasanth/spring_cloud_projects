package com.packs.counproc.MongoServer.models.RegisterModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@Document
public class RegisterStudent {

    @Id
    String id;

    int regId;

    String studentName;
    String schoolName;
    int marksPercentage;
    @Email @NotBlank @NotNull
    String email;
    String counsellingCode;
    String counsellingDate;
    boolean called;
    boolean assigned;
    Date registeredOn;



}
