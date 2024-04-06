package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.port.out.JWTServiceOut;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTServiceAdapter implements JWTServiceOut {
    @Override
    public String generarTokenOut(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5*360000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validarTokenOut(String token, UserDetails userDetails) {
        final String username = extractUserNameOut(token);
        boolean expirar = isTokenExpired(token);
        return (username.equals(userDetails.getUsername()) && expirar);
    }

    @Override
    public String extractUserNameOut(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    //Métodos de apoyo
    //Generar token
    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("1a2b3c4d5e6f789abcdef0123456789abcdef0123456789abcdef0123456789AFSDEFSse3158341");
        return Keys.hmacShaKeyFor(key);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction){
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException{
       /* try{
            Key llave = getSignKey();
            return Jwts.parserBuilder().setSigningKey(llave).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "El token ha expirado");
        }*/
        Key llave = getSignKey();
        return Jwts.parserBuilder().setSigningKey(llave).build().parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token){
        return (new Date().before(extractClaims(token, Claims::getExpiration)));
    }
}
