package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.requests.ChooseCollege;
import com.packs.counproc.MongoServer.models.responses.ApiResponse;
import com.packs.counproc.MysqlServer.models.RegisterStudent;
import com.packs.counproc.MysqlServer.repositories.StudentRegRepository;
import com.packs.counproc.exceptions.ConflictException;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.utils.Utils;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

@Service
public class CounsellingService {
    @Autowired
    private StudentRegRepository studentRegRepository;

    @Autowired
    private MailServices mailServices;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Utils utils;

    public ResponseEntity<ApiResponseBody> getAllRegistredStudents() {
        List<RegisterStudent> studentList = studentRegRepository.findAll();
        ApiResponseBody apiResponseBody = new ApiResponseBody(studentList, "Students List");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> registerStudent(RegisterStudent registerStudent) {
        try {
            RegisterStudent student = studentRegRepository.save(registerStudent);
            ApiResponseBody apiResponseBody = new ApiResponseBody(student.getId(), "Your registrations ID");
            return ResponseEntity.ok(apiResponseBody);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getLocalizedMessage());
        }

    }

    public ResponseEntity<ApiResponseBody> sendInvites() {
        boolean flag = false;
        List<RegisterStudent> studentsList = studentRegRepository.findByCalledOrderByMarksPercentageDesc(false);

        if (studentsList.size() > 0)
            flag = sendEmail(studentsList);
        else
            flag = true;

        if (flag) {
            ApiResponseBody apiResponseBody = new ApiResponseBody("Emails sent to the students");
            return ResponseEntity.ok(apiResponseBody);
        } else {
            ApiResponseBody apiResponseBody = new ApiResponseBody(500, HttpStatus.INTERNAL_SERVER_ERROR, "Emails not send to the students");
            return ResponseEntity.ok(apiResponseBody);
        }

    }

    public boolean sendEmail(List<RegisterStudent> studentsList) {
        boolean flag = false;
        SimpleMailMessage mailConfig = new SimpleMailMessage();
        DateTime counsellingDate = new DateTime(2019, 8, 1, 9, 0, 0, 0);

        for (RegisterStudent student : studentsList) {
            student.setCounsellingCode(utils.getRandomCode("UID"));
            student.setCounsellingDate(counsellingDate.toString("dd-MMM-yyyy hh:mm a"));
            mailConfig.setTo(student.getEmail());
            mailConfig.setSubject("Counselling Invitation");
            mailConfig.setText("Hi your counselling code is " + student.getCounsellingCode() +
                    " and date is " + student.getCounsellingDate());
            javaMailSender.send(mailConfig);
            student.setCalled(true);
            // NEED TO UPDATE DATABASE
            flag = updateStuent(student);

            if (studentsList.indexOf(student) % 3 == 0)
                counsellingDate = counsellingDate.plusDays(1);
        }
        return flag;
    }

    private boolean updateStuent(RegisterStudent student) {
        studentRegRepository.save(student);
        return true;
    }


}
