package com.railvayticketiffice.security;

import com.railvayticketiffice.security.user.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenProvider {

    final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.jwtAuthenticationExpiration}")
    private Long jwtAuthenticationExpiration;

    public String generateAccessToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtAuthenticationExpiration);

        return generateToken(claims, expiration, JwtType.ACCESS);
    }

    private String generateToken(Claims claims, Date expiration, JwtType jwtType) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(expiration)
                .setIssuer(jwtType.toString())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            LOG.warn("Jwt token is expired - {}", e.getMessage());
            return false;
        }
    }

    public String getSubject(String token) {
        return retrieveClaimsFromToken(token).getSubject();
    }

    public Date getExpiration(String token) {
        return retrieveClaimsFromToken(token).getExpiration();
    }

    private Claims retrieveClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}

