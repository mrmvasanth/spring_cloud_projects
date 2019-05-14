package com.example.easykiosk.controller;



import com.example.easykiosk.model.RequestModel;
import com.example.easykiosk.model.UserDetails;
import com.example.easykiosk.repository.KioskRepository;

import com.example.easykiosk.utils.BCryptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KioskController {

    @Autowired
    KioskRepository kioskRepository;

    BCryptUtils bCryptUtils=new BCryptUtils();

    @GetMapping("/getalluser")
    public List<UserDetails> getAllNotes() {
        return kioskRepository.findAll();
    }

    @GetMapping("/getuser/{id}")
    public Optional<UserDetails> getUser(@PathVariable(value="id") Long id)
    {
        Optional<UserDetails> userDetails=kioskRepository.findById(id);
        return userDetails;
    }

    @PostMapping("/adduser")
    public UserDetails addUser(@Valid @RequestBody UserDetails userDetails) {
        return kioskRepository.save(userDetails);
    }

    @PostMapping("/adduserdata")
    public UserDetails adduserdata(@RequestParam("file") MultipartFile file,@RequestParam("userdata") String userData , ModelMap modelMap) throws IOException {
        String userPassword;
        modelMap.addAttribute("file", file);
        UserDetails userDetails= new ObjectMapper().readValue(userData, UserDetails.class);
        userDetails.setImageurl(file.getOriginalFilename());
        userPassword=userDetails.getPassword();
        userDetails.setPassword(bCryptUtils.encodePassord(userPassword));
        return kioskRepository.save(userDetails);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody RequestModel requestModel) {
        Optional<UserDetails> user = kioskRepository.findById(requestModel.getId());
        System.out.println(user.toString());




    }




}
