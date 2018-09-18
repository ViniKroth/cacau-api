package com.cacau.api.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cacau.api.model.dto.PrizeMongoDTO;



public interface PrizeRepository extends MongoRepository <PrizeMongoDTO,String> {

}
