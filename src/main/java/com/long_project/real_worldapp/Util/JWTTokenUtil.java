package com.long_project.real_worldapp.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.long_project.real_worldapp.Entity.User;
import com.long_project.real_worldapp.Model.TokenPayLoad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenUtil {
    private final String secreString = "long_dz";

    public String generateToken(User user, long expiredDate){
        Map<String, Object> claims = new HashMap<>();
        TokenPayLoad tokenPayLoad = TokenPayLoad.builder().email(user.getEmail()).userId(user.getId()).build();
        claims.put("payload", tokenPayLoad);
        String token = (String)Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+ expiredDate*1000)).signWith(SignatureAlgorithm.HS512, secreString).compact();
        return token;
    }
}
