package com.packs.counproc.MysqlServer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "registerStudent")
@Table(uniqueConstraints ={@UniqueConstraint(columnNames = {"email"})})
public class RegisterStudent {

    @TableGenerator(name = "seq_gen", table = "seq_table",pkColumnName = "gen_name",valueColumnName = "value",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "seq_gen")
    int id;

    String studentName;

    String schoolName;

    int marksPercentage;

    @Email @NotBlank @NotNull
    String email;

    boolean called;

    boolean collegeAssigned;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredOn;

    String counsellingCode;

    String counsellingDate;

}
