package pidev.spring.security;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import pidev.spring.Service.Session_UserDetails;

@Component
@Slf4j
public class JwtUtils {

	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) throws UnsupportedEncodingException {

		Session_UserDetails userPrincipal = (Session_UserDetails) authentication.getPrincipal();

		return Jwts.builder()
				 
				.setSubject((userPrincipal. getEmail()+userPrincipal.getPassword()+userPrincipal.getAuthorities()))
				.setIssuedAt( new Date())
				.setExpiration(Date.from(Instant.ofEpochSecond(1612714383)))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		return Jwts.parser().setSigningKey("secretbezKoderSecretKey".getBytes("UTF-8")).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
 