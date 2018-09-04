package com.cacau.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cacau.api.model.dto.ServerMongoDTO;

public interface ServerRepository extends MongoRepository<ServerMongoDTO, String> {

}
