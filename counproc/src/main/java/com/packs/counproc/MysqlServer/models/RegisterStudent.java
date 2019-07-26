package com.packs.counproc.MysqlServer.models;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "registerStudent")
@Table(uniqueConstraints ={@UniqueConstraint(columnNames = {"email"})})
public class RegisterStudent {

    @TableGenerator(name = "seq_gen", table = "seq_table",pkColumnName = "gen_name",valueColumnName = "value",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "seq_gen")
    int id;

    @Pattern(regexp = "^[A-Za-z]+$",message = "Student name must contains alphabets only")
    @NotNull(message = "Student name cannot be null")
    @NotBlank (message = "Student name cannot be blank")
    @NotEmpty(message = "Student name cannot be empty")
    String studentName;

    String schoolName;

    @Min(value = 0,message = "Marks must not be less than 0")
    @Max(value = 100,message = "Marks must not be greater than 100")
    int marksPercentage;

    @Email @NotNull
    String email;

    boolean called;

    boolean collegeAssigned;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredOn;

    String counsellingCode;

    String counsellingDate;

}
