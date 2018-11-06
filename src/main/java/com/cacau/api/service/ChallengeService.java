package com.cacau.api.service;

import com.cacau.api.model.bo.ChallengeBO;
import com.cacau.api.model.dto.ChallengeMongoDTO;
import com.cacau.api.model.dto.ChallengeRequestDTO;
import com.cacau.api.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

	@Autowired
	ChallengeRepository challengeRepository;
	ChallengeBO challengeBO;

	public ChallengeRequestDTO create(ChallengeRequestDTO challenge) throws Exception {
		try {
			ChallengeMongoDTO challengeMongo = new ChallengeBO(challenge).toMongoDTO();
			return challengeRepository.insert(challengeMongo).toRequestDTO();

		} catch (Exception e) {
			throw e;
		}

	}

	public List<ChallengeRequestDTO> getAll() {
		ArrayList<ChallengeRequestDTO> challenges = new ArrayList<>();
		for (ChallengeMongoDTO c : challengeRepository.findAll()) {
			if (c.isActive()) {
				challenges.add(c.toRequestDTO());
			}
		}
		return challenges;
	}

	public Optional<ChallengeRequestDTO> get(String id) {
		Optional<ChallengeMongoDTO> challenge = challengeRepository.findById(id);
		Optional<ChallengeRequestDTO> challengeRequest = null;
		
		if (challenge.isPresent()) {
			challengeRequest = Optional.of(challenge.get().toRequestDTO());
		}
		return challengeRequest;
	}

	public void disable(String id) throws Exception {
		Optional<ChallengeMongoDTO> mongoChallenge = challengeRepository.findById(id);
		if (mongoChallenge.isPresent()) {
			mongoChallenge.get().setActive(false);
			challengeRepository.save(mongoChallenge.get());
		} else
			throw new Exception("ID_INFORMADA_INVALIDA");

	}

	public ChallengeRequestDTO update(String id, ChallengeRequestDTO challenge) throws Exception {
		Optional<ChallengeMongoDTO> mongoChallenge = challengeRepository.findById(id);
		if (mongoChallenge.isPresent()) {
			challengeBO = new ChallengeBO(mongoChallenge.get());
			challengeBO.updateWith(challenge);
			return challengeRepository.save(challengeBO.toMongoDTO()).toRequestDTO();

		} else {
			throw new Exception("ID_INFORMADA_INVALIDA");
		}

	}
}
