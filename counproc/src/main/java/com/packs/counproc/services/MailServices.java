package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.RegisterModel.RegisterStudent;
import com.packs.counproc.MongoServer.repositories.register.RegisterStudentRepo;
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



}
