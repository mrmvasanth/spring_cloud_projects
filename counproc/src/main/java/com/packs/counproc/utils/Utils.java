package com.packs.counproc.utils;


import com.packs.counproc.MongoServer.models.DatabaseSequence;
import com.packs.counproc.MongoServer.models.requests.ChooseCollege;
import com.packs.counproc.MongoServer.repositories.DatabaseSequenceRepo;
import com.packs.counproc.MongoServer.repositories.register.RegisterStudentRepo;
import com.packs.counproc.MysqlServer.models.RegisterStudent;
import com.packs.counproc.MysqlServer.repositories.StudentRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Utils {

    @Autowired
    private MongoOperations mongo;

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    @Autowired
    StudentRegRepository studentRegRepository;

    @Autowired
    DatabaseSequenceRepo databaseSequenceRepo;

    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String numerics = "0123456789";


    public int getIncCount(String modelName){
        DatabaseSequence  currentSequence=databaseSequenceRepo.findByModelName(modelName);
        int count=currentSequence.getSeq();
        currentSequence.setSeq(++count);
        databaseSequenceRepo.save(currentSequence);
        return currentSequence.getSeq();
    }

    public String getRandomCode(String code) {
        int len = 0;
        String key = null;
        if (code == "RID") {
            key = numerics;
            len = 4;
        } else if (code == "UID") {
            key = alphaNumericString;
            len = 6;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            // generate a random number between 0 to AlphaNumericString variable length
            int index = (int) (key.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(key.charAt(index));
        }
        return sb.toString();
    }

    public boolean validateStudent(ChooseCollege chooseCollege                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ) {
        Optional<RegisterStudent> student = studentRegRepository.findById(chooseCollege.getRegId());
        if (student.isPresent()) {
            if (student.get().getId()==(chooseCollege.getRegId()) &&
                    student.get().getCounsellingCode().equals(chooseCollege.getCounsellingCode()) &&
                    !student.get().isCollegeAssigned())
                return true;
            else
                return false;
        } else
            return false;
    }

}
