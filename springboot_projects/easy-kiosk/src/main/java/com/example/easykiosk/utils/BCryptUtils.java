package com.example.easykiosk.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtils {

    public String encodePassord(String password){
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);
            return hashedPassword;
    }

    public Boolean decodePassword(String password,String encryptedPassword){
        return encodePassord(password).toString().equals(encryptedPassword);

    }

}
