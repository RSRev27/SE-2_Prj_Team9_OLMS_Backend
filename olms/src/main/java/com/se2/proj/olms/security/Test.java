package com.se2.proj.olms.security;

/*
 * import io.jsonwebtoken.Jwts; import io.jsonwebtoken.SignatureAlgorithm;
 * import org.springframework.stereotype.Component;
 * 
 * import java.util.Date;
 * 
 * @Component public class Test {
 * 
 * private String secret = "secretKey"; // Use a secure key in production
 * 
 * public String generateToken(String username) { return Jwts.builder()
 * .setSubject(username) .setIssuedAt(new Date()) .setExpiration(new
 * Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
 * .signWith(SignatureAlgorithm.HS256, secret) .compact(); }
 * 
 * public boolean validateToken(String token, String username) { String
 * tokenUsername = extractUsername(token); return
 * (tokenUsername.equals(username) && !isTokenExpired(token)); }
 * 
 * public String extractUsername(String token) { return Jwts.parser()
 * .setSigningKey(secret) .parseClaimsJws(token) .getBody() .getSubject(); }
 * 
 * public boolean isTokenExpired(String token) { return Jwts.parser()
 * .setSigningKey(secret) .parseClaimsJws(token) .getBody()
 * .getExpiration().before(new Date()); } }
 */