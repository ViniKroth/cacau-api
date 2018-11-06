package com.cacau.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cacau.api.model.bo.PrizeBO;
import com.cacau.api.model.dto.PrizeMongoDTO;
import com.cacau.api.model.dto.PrizeRequestDTO;
import com.cacau.api.repository.PrizeRepository;

@Service
public class PrizeService {

	@Autowired
	PrizeRepository prizeRepository;
	
	PrizeBO prizeBO;
    
	public PrizeRequestDTO create(PrizeRequestDTO prize) throws Exception {
		try {
			PrizeMongoDTO prizeMongo = new PrizeBO(prize).toMongoDTO();
			return prizeRepository.insert(prizeMongo).toRequestDTO();

		} catch (Exception e) {
			throw e;
		}

	}

	public List<PrizeRequestDTO> getAll() {
		ArrayList<PrizeRequestDTO> prize = new ArrayList<>();
		for (PrizeMongoDTO u : prizeRepository.findAll()) {
			prize.add(u.toRequestDTO());
		}
		return prize;
	}

	public Optional<PrizeRequestDTO> get(String id) throws Exception {
		Optional<PrizeMongoDTO> prize = prizeRepository.findById(id);
		Optional<PrizeRequestDTO> prizeRequest = Optional.ofNullable(null);;
		if (prize.isPresent())
			prizeRequest = Optional.of(prize.get().toRequestDTO());
		return prizeRequest;


	}
}	

	
	

