package com.co.mundoviajero.business.Login;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenBusiness {

	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
    public static String generateToken(String user) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
        /*public static final String CLAVE_JWT = "62703757a09d19c112e19501f77a6faa008ef7e6b193d14afb136f4f8e2fc537244c62c8b5b8bcd64018d373e7160d785cf68e5b8cd9ea6e14f5a31671173189";
            
        */
        
        Long time = System.currentTimeMillis();
        String jwt = Jwts.builder()
                .signWith(key)
                .setSubject("Juan")
                .setExpiration(calendar.getTime())
                .setIssuedAt(new Date(time))
                .claim("email", user)
                .compact();        
        return jwt;
    }
    
    public static void processToken(String token){
    	String email;
    	try {
    		Claims claims = Jwts.parser()         
    			       .setSigningKey(key)
    			       .parseClaimsJws(token).getBody();     	    
    	       	     
    	} catch (JwtException ex) {       
    	    // Generate exception
    	    
    	}
    }
}