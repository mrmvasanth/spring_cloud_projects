package com.example.easykiosk.controller;


import com.example.easykiosk.model.RequestModel;
import com.example.easykiosk.model.UserDetails;
import com.example.easykiosk.repository.KioskRepository;
import com.example.easykiosk.utils.BCryptUtils;
import com.example.easykiosk.utils.ExcelGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KioskController {

    @Autowired
    KioskRepository kioskRepository;

    BCryptUtils bCryptUtils = new BCryptUtils();

    @GetMapping("/getalluser")
    public List<UserDetails> getAllNotes() {
        List<UserDetails> userDetails=kioskRepository.findAll();
        return userDetails;
    }

    @GetMapping("/getuser/{id}")
    public Optional<UserDetails> getUser(@PathVariable(value = "id") Long id) {
        Optional<UserDetails> userDetails = kioskRepository.findById(id);
        return userDetails;
    }

    @PostMapping("/adduserdata")
    public UserDetails adduserdata(@RequestParam("file") MultipartFile file, @RequestParam("userdata") String userData, ModelMap modelMap) throws IOException {
        String userPassword;
        modelMap.addAttribute("file", file);
        UserDetails userDetails = new ObjectMapper().readValue(userData, UserDetails.class);
        userDetails.setImageurl(file.getOriginalFilename());
        userPassword = userDetails.getPassword();
        userDetails.setPassword(bCryptUtils.encodePassord(userPassword));
        kioskRepository.save(userDetails);
        return userDetails;
    }

    @PostMapping("/login")
    public UserDetails getuserbyusername(@Valid @RequestBody RequestModel requestModel) {
        String originalPassword, encryptedPassword;
        Boolean checkPassword;
        originalPassword = requestModel.getPassword();
        UserDetails userDetails = kioskRepository.findByUsername(requestModel.getUsername());
        encryptedPassword = userDetails.getPassword();

        checkPassword = bCryptUtils.decodePassword(originalPassword, encryptedPassword);
        if (checkPassword) {
            userDetails.setImageurl("file:///home/administrator/Pictures/" + userDetails.getImageurl());
            return userDetails;
        } else {
            return null;
        }
    }

    @GetMapping("/downloadreport")
    public ResponseEntity<InputStreamResource> downloadreport()throws IOException{
        List<UserDetails> allUserDetails=kioskRepository.findAll();
        ByteArrayInputStream in = ExcelGenerator.usersToExcel(allUserDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }
}





