package com.cacau.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cacau.api.model.dto.ClientMongoDTO;

public interface ClientRepository extends MongoRepository<ClientMongoDTO, String> {

    @Query(value = "{ 'client_id' : ?0 }", fields = "{ 'name' : 1, 'client_id' : 1, 'client_secret' : 1, active: 1}" )
    ClientMongoDTO findClient(String clientId);
    
}
