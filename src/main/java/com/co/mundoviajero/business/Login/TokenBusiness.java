package com.co.mundoviajero.business.Login;

public class TokenBusiness {

    public static String generarToken(String usuario) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
        /*public static final String CLAVE_JWT = "62703757a09d19c112e19501f77a6faa008ef7e6b193d14afb136f4f8e2fc537244c62c8b5b8bcd64018d373e7160d785cf68e5b8cd9ea6e14f5a31671173189";
            String jwt = Jwts.builder().setSubject("users/TzMUocMF4p").setExpiration(calendar.getTime())
            .claim("name", usuario).signWith(SignatureAlgorithm.HS256, Constantes.CLAVE_JWT).compact();
            return jwt;
        */
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Long time = System.currentTimeMillis();
        String jwt = Jwts.builder()
                .signWith(key)
                .setSubject("Juan")
                .setExpiration(calendar.getTime())
                .setIssuedAt(new Date(time))
                .claim("email", person.getEmail())
                .claim("password", person.getPassword())
                .compact();
        
        return jwt;
    }
}