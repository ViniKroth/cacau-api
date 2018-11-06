package com.cacau.api.authentication;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.cacau.util.ApiKey;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

public class JWT {
    private final static String issuer = "br.com.cacau.api";
    private final static long ttlMillis = 7200000;

    // Sample method to construct a JWT
    public static String createJWT(String user_id, String username, String scope) {

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ApiKey.retrieveApiKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        // Issuer is now the Acess Scope of the user
        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(user_id).setIssuedAt(now).setSubject(username).setIssuer(scope)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        builder.claim("username", "vinicius.kroth@acad.pucr.br");

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(ApiKey.retrieveApiKey()))
                .parseClaimsJws(jwt).getBody();

        return claims;
    }

    public static long getMillis() {

        return ttlMillis;
    }

}
