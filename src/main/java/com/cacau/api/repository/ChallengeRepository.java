package com.cacau.api.repository;

import com.cacau.api.model.dto.ChallengeMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ChallengeRepository extends MongoRepository<ChallengeMongoDTO, String> {

}
