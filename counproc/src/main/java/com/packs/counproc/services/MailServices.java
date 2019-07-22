package com.packs.counproc.services;

import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.repositories.register.RegisterStudentRepo;
import com.packs.counproc.utils.Utils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    @Autowired
    Utils utils;

    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    String numerics="0123456789";

    boolean sendEmail(List<RegisterStudent> registerStudents) {
        boolean flag = false;
        SimpleMailMessage mailConfig = new SimpleMailMessage();
        String counsellingCode;
        DateTime counsellingDate = new DateTime(2019, 8, 1, 9, 0, 0, 0);

        // iterate and send 3 students per day
        for (RegisterStudent student : registerStudents) {
            System.out.println("Processing "+registerStudents.indexOf(student) +"/"+registerStudents.size());
            counsellingCode = utils.getRandomCode("UID");
            mailConfig.setTo(student.getEmail());
            mailConfig.setSubject("Counselling Invitation");
            mailConfig.setText("Hi your counselling code is " + counsellingCode +
                    " and date is "+counsellingDate.toString("dd-MMM-yyyy hh:mm a"));
            javaMailSender.send(mailConfig);

            flag = updateRegisterStudent(student, counsellingCode, counsellingDate);
            if (registerStudents.indexOf(student) % 3 == 0)
                counsellingDate=counsellingDate.plusDays(1);
        }
        return flag;
    }

    String generateVerificationCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int) (alphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    boolean updateRegisterStudent(RegisterStudent student, String code, DateTime date) {
        student.setCounsellingCode(code);
        student.setCounsellingDate(date.toString("dd-MMM-yyyy hh:mm a"));
        student.setCalled(true);
        registerStudentRepo.save(student);
        return true;
    }

}
