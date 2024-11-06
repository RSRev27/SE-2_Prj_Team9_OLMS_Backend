/*
 * package com.se2.proj.olms.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.data.mongodb.core.MongoTemplate; import
 * org.springframework.data.mongodb.core.query.Criteria; import
 * org.springframework.data.mongodb.core.query.Query; import
 * org.springframework.stereotype.Service;
 * 
 * import com.se2.proj.olms.entities.User;
 * 
 * @Service public class UserService {
 * 
 * @Autowired private MongoTemplate mongoTemplate;
 * 
 * public User getUserByUsername(String username) { Query query = new
 * Query(Criteria.where("username").is(username)); return
 * mongoTemplate.findOne(query, User.class); } }
 */