package com.webencyclop.demo.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

import com.webencyclop.demo.auth.exception.JwtException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
//	@Value("${auth.signingKey}")
    private static String signingKey="privatekey";

    public static String generateToken(String iss,Date now,Date exp,String subject,long id,int roleId) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuer(iss)
                .setIssuedAt(now)   
                .setExpiration(exp)
                .claim("id", id)
                .claim("roleId", roleId)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    public static String getSubject(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("authorization");
        if(token == null) return null;
        try {
        	return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        }catch(Exception e){
        	throw new JwtException();
        }        
    }
    
}