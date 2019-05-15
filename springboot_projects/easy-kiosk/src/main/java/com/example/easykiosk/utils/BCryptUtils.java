package com.example.easykiosk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtils {
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public String encodePassord(String password){

            String hashedPassword = passwordEncoder.encode(password);
            return hashedPassword;
    }

    public Boolean decodePassword(String password,String encryptedPassword){

        return passwordEncoder.matches(password,encryptedPassword);

    }

}
