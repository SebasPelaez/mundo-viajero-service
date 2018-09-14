package com.co.mundoviajero.business.Login;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import com.co.mundoviajero.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenBusiness {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
    @SuppressWarnings("deprecation")
	public static String generateToken(String user) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 3);
        
        Long time = System.currentTimeMillis();
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.JWT_Key)
                .setSubject("Juan")
                .setExpiration(calendar.getTime())
                .setIssuedAt(new Date(time))
                .claim("email", user)
                .compact();        
        return jwt;
    }
}