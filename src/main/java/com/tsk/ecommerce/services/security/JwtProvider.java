package com.tsk.ecommerce.services.security;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.tsk.ecommerce.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	public String generateToken(String login) {
		//Date d = Date.from(LocalDate.now() .atStartOfDay(ZoneId.systemDefault()).toInstant());
		return Jwts.builder()
					.setSubject(login)
					.setExpiration(new Date(System.currentTimeMillis() + (3 * 60 * 60 * 1000)))
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					.compact();
	}

	private PrivateKey getPrivateKey() throws Exception {
		try {return (PrivateKey) KeyStore.getInstance("tsk");}
		catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	
	/*********************************
	 * verifier si le token est valide
	 */
	
	public boolean validateToken(String token) throws UnauthorizedException{
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (Exception exception){
			exception.getStackTrace();
			return false;
		}
	}

	/************************
	 * get username par token
	 */
	public String getLoginFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

}
