package com.packs.counproc.utils;

import com.packs.counproc.models.CustomSequences;
import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.models.requests.ChooseCollege;
import com.packs.counproc.repositories.RegisterStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class Utils {

    @Autowired
    private MongoOperations mongo;

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    String numerics = "0123456789";


    public int getNextSequence(String seqName)
    {
        CustomSequences counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);
        return counter.getSeq();
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
        List<RegisterStudent> student = registerStudentRepo.findByRegId(chooseCollege.getRegId());
        if (!student.isEmpty()) {
            if (student.get(0).getRegId().equals(chooseCollege.getRegId()) &&
                    student.get(0).getCounsellingCode().equals(chooseCollege.getCounsellingCode()) &&
                    !student.get(0).isAssigned())
                return true;
            else
                return false;
        } else
            return false;
    }

}
