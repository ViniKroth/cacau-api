package com.cacau.api.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cacau.api.model.dto.UserAuthorizationInfoDTO;
import com.cacau.api.model.dto.UserMongoDTO;

@Repository
public interface UserRepository extends MongoRepository<UserMongoDTO, String> {
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'creationDate' : 1, _id:false}")
    Date findCreationDateForUser(String id);

    @Query(value = "{ 'email' : ?0 }", fields = "{ 'email' : 1, 'password' : 1}")
    UserAuthorizationInfoDTO retrieveLoggedUser(String email);
}
