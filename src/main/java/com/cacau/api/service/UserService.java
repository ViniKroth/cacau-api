package com.cacau.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cacau.api.model.dto.UserMongoDTO;
import com.cacau.api.model.dto.UserRequestDTO;
import com.cacau.api.model.bo.UserBO;
import com.cacau.api.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	UserBO userBO;

	public UserRequestDTO create(UserRequestDTO user) throws Exception {
		try {
			UserMongoDTO newUser = new UserBO(user).toMongoDTO();
			UserMongoDTO createdUser = userRepository.insert(newUser);
			return createdUser.toRequestDTO();
		} catch (Exception e) {
			throw e;
		}
	}

	public List<UserRequestDTO> getAll() {
		ArrayList<UserRequestDTO> users = new ArrayList<>();
		for (UserMongoDTO retrievedUser : userRepository.findAll()) {
			if (retrievedUser.isActive()) {
				users.add(retrievedUser.toRequestDTO());
			}
		}
		return users;
	}

	public Optional<UserRequestDTO> get(String id) throws Exception {
		Optional<UserMongoDTO> retrievedUser = userRepository.findById(id);
		if (retrievedUser.isPresent())
			return Optional.of(retrievedUser.get().toRequestDTO());
		else
			return Optional.ofNullable(null);
	}

	public UserRequestDTO replace(UserRequestDTO user, String id) throws Exception {
		try {
			Optional<UserMongoDTO> oldMongoUser = userRepository.findById(id);
			if (oldMongoUser.isPresent()) {
				UserBO userBO = new UserBO(oldMongoUser.get());
				userBO.updateWith(user);
				return userRepository.save(userBO.toMongoDTO()).toRequestDTO();
			} else {
				throw new Exception("ID_INFORMADA_INVALIDA");
			}
		} catch (Exception e) {
			throw e;
		}
	}

    public UserRequestDTO update(UserRequestDTO user, String id) throws Exception {
        Optional<UserMongoDTO> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isPresent()) {
            userBO = new UserBO(retrievedUser.get());
            userBO.updateWith(user);
            return userRepository.save(userBO.toMongoDTO()).toRequestDTO();
        } else
            throw new Exception("ID_INFORMADA_INVALIDA");
    }

    public void disable(String id) throws Exception {
		Optional<UserMongoDTO> retrievedUser = userRepository.findById(id);
		if (retrievedUser.isPresent()) {
            retrievedUser.get().setActive(false);
			userRepository.save(retrievedUser.get());
		} else
			throw new Exception("ID_INFORMADA_INVALIDA");

	}

}
