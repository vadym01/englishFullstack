package com.englishapp.demoen.security.jwt;

import com.englishapp.demoen.entity.Role;
import com.englishapp.demoen.entity.User;
import com.englishapp.demoen.repository.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.englishapp.demoen.InfoProvider.SECRET_TOKEN_KEY;
import static com.englishapp.demoen.InfoProvider.VALID_TOKEN_EXPIRATION;

@Component
public class JwtTokenProvider {

    @Autowired
    private UserRepository userRepository;

    //create token
    public String generateToken(Authentication authentication){
        ///////wtong
        String userName = authentication.getName();
        User user =  userRepository.findByUsername(userName);
        ////
//        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + VALID_TOKEN_EXPIRATION);
        String userId = Long.toString(user.getId());

        Set<String> userRoles = new HashSet<>();
        for(Role role : user.getRoles()){
            userRoles.add(role.getName());
        }

        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("id",userId);
        customClaims.put("username", user.getUsername());
        customClaims.put("roles", userRoles.toArray());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(customClaims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET_TOKEN_KEY)
                .compact();
    }

    //validate token
    public boolean validToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_TOKEN_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid jwt signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid jwt token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired jwt token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported jwt exception");
        }catch (IllegalArgumentException ex){
            System.out.println("Illegal argument exception");
        }
        return false;
    }

//    getUserIdFromToken
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_TOKEN_KEY).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }

}
