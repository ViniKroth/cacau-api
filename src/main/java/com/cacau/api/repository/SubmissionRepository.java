package com.cacau.api.repository;

import com.cacau.api.model.dto.SubmissionMongoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubmissionRepository extends MongoRepository<SubmissionMongoDTO, String> {

}
