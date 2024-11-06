package com.se2.proj.olms.service;

/*
 * import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm;
 * import org.springframework.security.core.Authentication; import
 * org.springframework.stereotype.Service;
 * 
 * import java.util.Date;
 * 
 * @Service public class JwtUtil {
 * 
 * private final String SECRET_KEY = "Please don't ask me"; private final long
 * EXPIRATION_TIME = 86400000;
 * 
 * public String generateToken(Authentication authentication) { return
 * Jwts.builder() .setSubject(authentication.getName()) .setIssuedAt(new Date())
 * .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
 * .signWith(SignatureAlgorithm.HS256, SECRET_KEY) .compact(); }
 * 
 * public boolean validateToken(String token, String username) { String
 * tokenUsername = extractUsername(token); return
 * (tokenUsername.equals(username) && !isTokenExpired(token)); }
 * 
 * public String extractUsername(String token) { return Jwts.parser()
 * .setSigningKey(SECRET_KEY) .parseClaimsJws(token) .getBody() .getSubject(); }
 * 
 * public boolean isTokenExpired(String token) { return Jwts.parser()
 * .setSigningKey(SECRET_KEY) .parseClaimsJws(token) .getBody()
 * .getExpiration().before(new Date()); } }
 */