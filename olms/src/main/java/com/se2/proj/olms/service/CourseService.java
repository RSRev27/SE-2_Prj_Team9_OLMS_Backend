package com.se2.proj.olms.service;

import org.bson.types.ObjectId;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.entities.Register;

@Service
public class CourseService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Register getUserName(String userID) {
        Query query = new Query();
        ObjectId objId = new ObjectId(userID);
        query.addCriteria(Criteria.where("_id").is(objId));
        return mongoTemplate.find(query, Register.class).get(0);

        //return null;
    }

    public Object[] getTAList() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userType").is("TA"));
        return mongoTemplate.find(query, Register.class).toArray();

        //return null;
    }

}
