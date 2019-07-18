package com.packs.myds.controllers;

import com.packs.myds.services.DocuSignServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("api/ds/")
public class DocuSignController {

    @Autowired
    DocuSignServices docuSignServices;

    @PostMapping("/signfile")
    public String signDocument(@RequestParam("file") MultipartFile file)throws Exception{
        String returnUrl= docuSignServices.signFile(file);
        return returnUrl;
    }

    @GetMapping("/getaccesstoken")
    public String getaccesstoken() throws Exception{
        return docuSignServices.getAccessToken();
    }

    @GetMapping("/parsecode")
    public String parseCode(@RequestParam("code") String code) throws Exception{
        String accessToken=docuSignServices.parseToken(code);
        System.out.println("AccessToken =============== "+accessToken);
        return accessToken;
    }

}
