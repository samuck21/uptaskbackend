package com.samuck21.uptaskbackend.utils;

import com.samuck21.uptaskbackend.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey key = Keys.hmacShaKeyFor("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30".getBytes(StandardCharsets.UTF_8));
    private Claims getClaims(String token){
        return  Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token){
     return getClaims(token).getSubject();
    }
    private  boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }

    public String genereteToken(User user){
        long expirationMillis= 1000*60*60;
        Date now = new Date();
        Date expiry = new Date(now.getTime()+expirationMillis);
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }
}
