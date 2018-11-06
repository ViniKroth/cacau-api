package com.cacau.api.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cacau.api.authentication.Roles;
import com.cacau.api.model.Token;
import com.cacau.api.model.dto.ClientMongoDTO;
import com.cacau.api.model.dto.UserAuthorizationInfoDTO;
import com.cacau.api.model.dto.UserMongoDTO;
import com.cacau.api.repository.ClientRepository;
import com.cacau.api.repository.UserRepository;
import io.jsonwebtoken.Claims;


import java.util.Base64;
import java.util.Date;

@Service
public class AuthorizationUtil {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public void validateClient(String clientHeader) throws Exception {
        try {
            // Just to remove header, provisory
            if (clientHeader.contains("Basic"))
                clientHeader = clientHeader.substring(6);
            byte[] valueDecoded = Base64.getDecoder().decode(clientHeader);
            String decryptedHeader = new String(valueDecoded);
            String[] clientValues = decryptedHeader.split(":");
            ClientMongoDTO client = clientRepository.findClient(clientValues[0]);
            if (!client.isActive()) {
                throw new Exception("DISABLED_CLIENT");
            }
            // Extremely slow
            if (!bcrypt.matches(clientValues[1], client.getClientSecret())) {
                throw new Exception("CLIENT_CREDENTIALS_DOES_NOT_EXIST");
            }
        } catch (Exception e) {
            throw new Exception("CLIENT_CREDENTIALS_DOES_NOT_EXIST");
        }

    }

    // Make two step verification, if a user is admin, the first time by scope, and
    // the second time by checking from the user retrieved from the database, and if
    // doesnt match, disable him. Also check if everyone is active
    //
    public void validateToken(String acessToken, String scope) throws Exception {
       acessToken = acessToken.replaceAll("\"","" );
       acessToken = acessToken.substring(7);
        Claims claim = JWT.parseJWT(acessToken);
        if (new Date().getTime() > claim.getExpiration().getTime()) {
            throw new Exception("EXPIRED_TOKEN");
        }
        if (scope.equals(Roles.ADMIN.toString())) {
            if (!claim.getIssuer().equals("ADMIN")) {
                throw new Exception("UNAUTHORIZED_USER");
            }
        }

    }

    public UserMongoDTO validateLoggedUser(UserAuthorizationInfoDTO claimedUser) throws Exception {
        try {

            UserAuthorizationInfoDTO retriavedUser = userRepository.retrieveLoggedUser(claimedUser.getEmail().get());
            if (!bcrypt.matches(claimedUser.getPassword().get(), retriavedUser.getPassword().get())) {
                throw new Exception("INVALID_USER_OR_PASSWORD");
            }

            UserMongoDTO validatedUser = userRepository.findById(retriavedUser.getId()).get();
            if (!validatedUser.isActive()) {
                throw new Exception("DISABLED_USER");
            }
            return validatedUser;
        } catch (Exception e) {
            if (!e.getMessage().equals("DISABLED_USER"))
                throw new Exception("INVALID_USER_OR_PASSWORD");
            else
                throw e;
        }

    }
}
