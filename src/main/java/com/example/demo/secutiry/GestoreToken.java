package com.example.demo.secutiry;

import com.example.demo.exception.PasswordErrataException;
import com.example.demo.exception.UtenteBloccatoException;
import com.example.demo.model.Utente;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class GestoreToken {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Utente userDetails) {
        return generateToken(creaClaims(userDetails));
    }

    public String generateToken(Claims extraClaims) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims creaClaims(Utente u){
        String ruolo=u.getRuolo().getNome();
        Claims claims = Jwts.claims().setSubject(u.getEmail());
        if(ruolo.startsWith("ROLE_"))ruolo=ruolo.substring(5);
        claims.put("ruolo",ruolo);
        return claims;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        Utente u=(Utente) userDetails;
        final String username = extractUsername(token);
        String email=u.getEmail();
        boolean b1=username.equals(email);
        boolean b2= !isTokenExpired(token);
        boolean b3=userDetails.isAccountNonExpired();
        return b1&&b2&&b3;
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token)&& userDetails.isAccountNonExpired();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}